package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import java.util.*;
import lombok.Getter;
import stelnet.board.query.provider.ShipQueryProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.board.query.view.dialog.ShipPickerDialog;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

@Getter
public class ShipQueryFactory extends QueryFactory {

    private transient ShipQueryProvider provider = new ShipQueryProvider();

    private final FilteringButton[] classSizes = ShipButtonUtils.getClassSizes();
    private final FilteringButton[] mountSmall = ShipButtonUtils.getMountTypes(WeaponSize.SMALL);
    private final FilteringButton[] mountMedium = ShipButtonUtils.getMountTypes(WeaponSize.MEDIUM);
    private final FilteringButton[] mountLarge = ShipButtonUtils.getMountTypes(WeaponSize.LARGE);
    private final FilteringButton[] mountBays = ShipButtonUtils.getMountBays(this);
    private final FilteringButton[] designTypes = ShipButtonUtils.getManufacturers(provider);
    private final FilteringButton[] builtIns = ShipButtonUtils.getBuiltIns(provider);

    public Object readResolve() {
        provider = new ShipQueryProvider();
        return this;
    }

    public void setFighterBays(final FighterBaysButton active) {
        final Iterable<FilteringButton> iterable = Arrays.asList(mountBays);
        final Iterator<FilteringButton> iterator = iterable.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            final FilteringButton button = iterator.next();
            if (!active.equals(button)) {
                button.setStateOn(active.equals(button));
            }
        }
    }

    @Override
    public Set<Filter> getFilters() {
        final Set<Filter> filters = getCommonFilters();
        addSelectedOrNone(filters, builtIns, L10n.query("BUILT_IN"), true);
        return filters;
    }

    public void prepareBuiltIns() {
        final Set<Filter> filters = getCommonFilters();
        final Set<String> hullModIds = getProvider().getBuiltInIds(filters);
        hullModIds.add("None");
        for (final FilteringButton button : getBuiltIns()) {
            button.updateVisibility(hullModIds);
        }
    }

    @Override
    protected Button[] getFinalComponents() {
        final Set<Filter> filters = getFilters();
        final PickerDialog picker = new ShipPickerDialog(provider.getMatching(filters), this);
        return new Button[] { new FindMatchingButton(this, L10n.common("SHIPS")), new FindSelectedButton(picker) };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        prepareBuiltIns();
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, L10n.query("CLASS_SIZE"), classSizes, true));
        final FilteringButton[] allMountSizeType = CollectionUtils.merge(mountSmall, mountMedium, mountLarge);
        elements.add(
            new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("WEAPON_MOUNTS"), true, allMountSizeType)
        );
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MOUNT_SIZE_SMALL"), mountSmall, true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MOUNT_SIZE_MEDIUM"), mountMedium, true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("MOUNT_SIZE_LARGE"), mountLarge, true));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("FIGHTER_BAYS"), mountBays, true));
        elements.add(
            new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("MANUFACTURERS"), true, designTypes)
        );
        elements.add(new ButtonGroup(sizeHelper, designTypes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), L10n.query("BUILT_IN"), true, builtIns));
        elements.add(new ButtonGroup(sizeHelper, builtIns, true));
        return elements;
    }

    protected Set<Filter> getCommonFilters() {
        final Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, classSizes, L10n.query("CLASS_SIZE"));
        final FilteringButton[] allMountSizeType = CollectionUtils.merge(mountSmall, mountMedium, mountLarge);
        addSelectedOrNone(filters, allMountSizeType, L10n.query("MOUNT_SIZE_TYPE"), true);
        addSelectedOrNone(filters, mountBays, L10n.query("FIGHTER_BAYS"), true);
        addSelectedOrNone(filters, designTypes, L10n.query("MANUFACTURERS"), true);
        return filters;
    }
}
