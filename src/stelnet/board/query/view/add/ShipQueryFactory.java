package stelnet.board.query.view.add;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ShipProvider;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowShips;
import uilib.property.Size;

public class ShipQueryFactory extends QueryFactory {

    @Getter
    private final ShipProvider provider = new ShipProvider(this);

    private final FilteringButton[] classSizes = ShipButtonUtils.getClassSizes();
    private final FilteringButton[] mountSizes = ShipButtonUtils.getMountSizes();
    private final FilteringButton[] mountTypes = ShipButtonUtils.getMountTypes();
    private final FilteringButton[] mountBays = ShipButtonUtils.getMountBays(this);
    private final FilteringButton[] designTypes = ShipButtonUtils.getManufacturers(provider);
    private final FilteringButton[] builtIns = ShipButtonUtils.getBuiltIns(provider);
    private final FilteringButton[] dMods = ShipButtonUtils.getDMods(provider);

    public void addDmodFilters(Set<Filter> filters) {
        addSelectedOrNone(filters, dMods, L10n.get(QueryL10n.DMODS), true);
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
    public Set<Filter> getFilters(boolean forResults) {
        Set<Filter> filters = getCommonFilters();
        addSelectedOrNone(filters, builtIns, L10n.get(QueryL10n.BUILT_IN), true);
        if (forResults) {
            addDmodFilters(filters);
        }
        return filters;
    }

    @Override
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return new ShowShips(
            getShips(filters),
            L10n.get(QueryL10n.MATCHING_SHIPS),
            L10n.get(QueryL10n.NO_MATCHING_SHIPS),
            size
        );
    }

    @Override
    protected Button[] getFinalComponents() {
        Set<Filter> filters = getFilters(false);
        return new Button[] {
            new FindMatchingButton(this, L10n.get(CommonL10n.SHIPS)),
            new FindSelectedButton(this, getShips(filters)),
        };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        prepareBuiltIns();
        List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.CLASS_SIZE, classSizes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.WEAPON_MOUNTS, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_TYPE, mountTypes, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE, mountSizes, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.FIGHTER_BAYS, mountBays, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANUFACTURERS, true, designTypes));
        elements.add(new ButtonGroup(sizeHelper, designTypes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.DMODS, true, dMods));
        elements.add(new ButtonGroup(sizeHelper, dMods, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.BUILT_IN, true, builtIns));
        elements.add(new ButtonGroup(sizeHelper, builtIns, true));
        return elements;
    }

    private Set<Filter> getCommonFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, classSizes, L10n.get(QueryL10n.CLASS_SIZE));
        addSelectedOrNone(filters, mountSizes, L10n.get(QueryL10n.MOUNT_SIZE), true);
        addSelectedOrNone(filters, mountTypes, L10n.get(QueryL10n.MOUNT_TYPE), true);
        addSelectedOrNone(filters, mountBays, L10n.get(QueryL10n.FIGHTER_BAYS), true);
        addSelectedOrNone(filters, designTypes, L10n.get(QueryL10n.MANUFACTURERS), true);
        return filters;
    }

    private List<FleetMemberAPI> getShips(Set<Filter> filters) {
        return provider.getMatching(filters);
    }

    private void prepareBuiltIns() {
        Set<Filter> filters = getCommonFilters();
        Set<String> hullModIds = provider.getBuiltInIds(filters);
        hullModIds.add("None");
        for (FilteringButton button : builtIns) {
            button.updateVisibility(hullModIds);
        }
    }
}
