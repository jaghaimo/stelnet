package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ShipProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.board.query.view.dialog.ShipPickerDialog;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

@Getter
public class ShipQueryFactory extends QueryFactory {

    private transient ShipProvider provider = new ShipProvider();

    private final FilteringButton[] classSizes = ShipButtonUtils.getClassSizes();
    private final FilteringButton[] mountSmall = ShipButtonUtils.getMountTypes(WeaponSize.SMALL);
    private final FilteringButton[] mountMedium = ShipButtonUtils.getMountTypes(WeaponSize.MEDIUM);
    private final FilteringButton[] mountLarge = ShipButtonUtils.getMountTypes(WeaponSize.LARGE);
    private final FilteringButton[] mountBays = ShipButtonUtils.getMountBays(this);
    private final FilteringButton[] designTypes = ShipButtonUtils.getManufacturers(provider);
    private final FilteringButton[] builtIns = ShipButtonUtils.getBuiltIns(provider);

    public Object readResolve() {
        provider = new ShipProvider();
        return this;
    }

    public void setFighterBays(FighterBaysButton active) {
        Iterable<FilteringButton> iterable = Arrays.asList(mountBays);
        Iterator<FilteringButton> iterator = iterable.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            FilteringButton button = iterator.next();
            if (!active.equals(button)) {
                button.setStateOn(active.equals(button));
            }
        }
    }

    @Override
    public Set<Filter> getFilters() {
        Set<Filter> filters = getCommonFilters();
        addSelectedOrNone(filters, builtIns, L10n.get(QueryL10n.BUILT_IN), true);
        return filters;
    }

    @Override
    protected Button[] getFinalComponents() {
        Set<Filter> filters = getFilters();
        PickerDialog picker = new ShipPickerDialog(provider.getMatching(filters), this);
        return new Button[] {
            new FindMatchingButton(this, L10n.get(CommonL10n.SHIPS)),
            new FindSelectedButton(picker),
        };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        prepareBuiltIns();
        List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.CLASS_SIZE, classSizes, true));
        FilteringButton[] allMountSizeType = CollectionUtils.merge(mountSmall, mountMedium, mountLarge);
        elements.add(
            new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.WEAPON_MOUNTS, true, allMountSizeType)
        );
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE_SMALL, mountSmall, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE_MEDIUM, mountMedium, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE_LARGE, mountLarge, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.FIGHTER_BAYS, mountBays, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANUFACTURERS, true, designTypes));
        elements.add(new ButtonGroup(sizeHelper, designTypes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.BUILT_IN, true, builtIns));
        elements.add(new ButtonGroup(sizeHelper, builtIns, true));
        return elements;
    }

    protected Set<Filter> getCommonFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, classSizes, L10n.get(QueryL10n.CLASS_SIZE));
        FilteringButton[] allMountSizeType = CollectionUtils.merge(mountSmall, mountMedium, mountLarge);
        addSelectedOrNone(filters, allMountSizeType, L10n.get(QueryL10n.MOUNT_SIZE_TYPE), true);
        addSelectedOrNone(filters, mountBays, L10n.get(QueryL10n.FIGHTER_BAYS), true);
        addSelectedOrNone(filters, designTypes, L10n.get(QueryL10n.MANUFACTURERS), true);
        return filters;
    }

    public void prepareBuiltIns() {
        Set<Filter> filters = getCommonFilters();
        Set<String> hullModIds = getProvider().getBuiltInIds(filters);
        hullModIds.add("None");
        for (FilteringButton button : getBuiltIns()) {
            button.updateVisibility(hullModIds);
        }
    }
}
