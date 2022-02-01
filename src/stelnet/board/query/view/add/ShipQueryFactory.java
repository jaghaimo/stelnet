package stelnet.board.query.view.add;

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
import stelnet.board.query.view.dialog.PickerDialog;
import stelnet.board.query.view.dialog.ShipPickerDialog;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalOr;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

@Getter
public class ShipQueryFactory extends QueryFactory {

    private transient ShipProvider provider = new ShipProvider();
    private transient ShipButtonHelper helper = new ShipButtonHelper(this);

    private final FilteringButton[] classSizes = ShipButtonUtils.getClassSizes();
    private final FilteringButton[] mountSizes = ShipButtonUtils.getMountSizes();
    private final FilteringButton[] mountTypes = ShipButtonUtils.getMountTypes();
    private final FilteringButton[] mountBays = ShipButtonUtils.getMountBays(this);
    private final FilteringButton[] designTypes = ShipButtonUtils.getManufacturers(provider);
    private final FilteringButton[] builtIns = ShipButtonUtils.getBuiltIns(provider);
    private final FilteringButton[] dModCount = ShipButtonUtils.getDModsCount();
    private final FilteringButton[] dModAllowed = ShipButtonUtils.getDMods(provider);

    public Object readResolve() {
        provider = new ShipProvider();
        helper = new ShipButtonHelper(this);
        return this;
    }

    public void addDmodFilters(Set<Filter> filters) {
        addSelectedOrNone(filters, dModCount, L10n.get(QueryL10n.DMOD_COUNT), true);
        Set<Filter> allowedDmods = getFilters(dModAllowed, true);
        Set<Filter> disallowedDmods = getFilters(dModAllowed, false);
        if (!hasDmodSelection()) {
            return;
        }
        filters.add(new LogicalOr(allowedDmods, L10n.get(QueryL10n.DMOD_ALLOWED)));
        filters.add(new LogicalAnd(disallowedDmods, L10n.get(QueryL10n.DMOD_DISALLOWED)));
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
    protected Button[] getFinalComponents() {
        Set<Filter> filters = getFilters(false);
        PickerDialog picker = new ShipPickerDialog(provider.getMatching(filters), this);
        return new Button[] {
            new FindMatchingButton(this, L10n.get(CommonL10n.SHIPS)),
            new FindSelectedButton(picker),
        };
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        helper.prepareBuiltIns();
        helper.prepareDmods();
        List<Renderable> elements = new LinkedList<>();
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.CLASS_SIZE, classSizes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.WEAPON_MOUNTS, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_TYPE, mountTypes, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.MOUNT_SIZE, mountSizes, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.FIGHTER_BAYS, mountBays, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANUFACTURERS, true, designTypes));
        elements.add(new ButtonGroup(sizeHelper, designTypes, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.DMODS, true, dModAllowed));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.DMOD_COUNT, dModCount, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.DMOD_ALLOWED, dModAllowed));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.BUILT_IN, true, builtIns));
        elements.add(new ButtonGroup(sizeHelper, builtIns, true));
        return elements;
    }

    protected Set<Filter> getCommonFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addSelectedOrAll(filters, classSizes, L10n.get(QueryL10n.CLASS_SIZE));
        addSelectedOrNone(filters, mountSizes, L10n.get(QueryL10n.MOUNT_SIZE), true);
        addSelectedOrNone(filters, mountTypes, L10n.get(QueryL10n.MOUNT_TYPE), true);
        addSelectedOrNone(filters, mountBays, L10n.get(QueryL10n.FIGHTER_BAYS), true);
        addSelectedOrNone(filters, designTypes, L10n.get(QueryL10n.MANUFACTURERS), true);
        return filters;
    }

    protected boolean hasDmodSelection() {
        Set<Filter> allowedDmods = getFilters(dModAllowed, true);
        Set<Filter> disallowedDmods = getFilters(dModAllowed, false);
        return !allowedDmods.isEmpty() && !disallowedDmods.isEmpty();
    }
}
