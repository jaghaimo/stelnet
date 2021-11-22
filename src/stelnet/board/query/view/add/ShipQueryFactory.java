package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.provider.ShipProvider;
import stelnet.filter.Filter;
import stelnet.filter.FleetMemberHasDMod;
import stelnet.filter.FleetMemberPristine;
import stelnet.filter.ShipHullHasBays;
import stelnet.filter.ShipHullHasBuiltIn;
import stelnet.filter.ShipHullIsManufacturer;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.ShipHullNoBuiltIns;
import stelnet.filter.WeaponSlotIsSize;
import stelnet.filter.WeaponSlotIsType;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowShips;
import uilib.property.Size;

public class ShipQueryFactory extends QueryFactory {

    private final ShipProvider shipProvider = new ShipProvider(this);
    private final FilteringButton[] classSizes = createClassSizes();
    private final FilteringButton[] mountSizes = createMountSizes();
    private final FilteringButton[] mountTypes = createMountTypes();
    private final FilteringButton[] mountBays = createMountBays();
    private final FilteringButton[] manufacturers = createManufacturers();
    private final FilteringButton[] builtIns = createBuiltIns();
    private final FilteringButton[] dMods = createDMods();

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
        addToFilters(filters, builtIns, L10n.get(QueryL10n.BUILT_IN), true);
        if (forResults) {
            addToFilters(filters, dMods, L10n.get(QueryL10n.DMODS), true);
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
    public QueryProvider getProvider() {
        return shipProvider;
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
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.MANUFACTURERS, true));
        elements.add(new ButtonGroup(sizeHelper, manufacturers, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.DMODS, true));
        elements.add(new ButtonGroup(sizeHelper, dMods, true));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.BUILT_IN, true));
        elements.add(new ButtonGroup(sizeHelper, builtIns, true));
        return elements;
    }

    private FilteringButton[] createClassSizes() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(HullSize.FRIGATE), new ShipHullIsSize(HullSize.FRIGATE)),
            new FilteringButton(L10n.get(HullSize.DESTROYER), new ShipHullIsSize(HullSize.DESTROYER)),
            new FilteringButton(L10n.get(HullSize.CRUISER), new ShipHullIsSize(HullSize.CRUISER)),
            new FilteringButton(L10n.get(HullSize.CAPITAL_SHIP), new ShipHullIsSize(HullSize.CAPITAL_SHIP)),
        };
    }

    private FilteringButton[] createManufacturers() {
        List<FilteringButton> manufacturers = new LinkedList<>();
        for (String manufacturer : shipProvider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new ShipHullIsManufacturer(manufacturer), false));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    private FilteringButton[] createMountBays() {
        List<FilteringButton> manufacturers = new LinkedList<>();
        manufacturers.add(new FilteringButton(CommonL10n.NONE, new ShipHullHasBays(0)));
        for (int i = 1; i < 7; i++) {
            manufacturers.add(new FighterBaysButton(this, String.valueOf(i), new ShipHullHasBays(i)));
        }
        manufacturers.get(1).setStateOn(true);
        return manufacturers.toArray(new FilteringButton[] {});
    }

    private FilteringButton[] createMountSizes() {
        return new FilteringButton[] {
            new FilteringButton(WeaponSize.SMALL.getDisplayName(), new WeaponSlotIsSize(WeaponSize.SMALL)),
            new FilteringButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponSlotIsSize(WeaponSize.MEDIUM)),
            new FilteringButton(WeaponSize.LARGE.getDisplayName(), new WeaponSlotIsSize(WeaponSize.LARGE)),
        };
    }

    private FilteringButton[] createMountTypes() {
        return new FilteringButton[] {
            new FilteringButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponSlotIsType(WeaponType.BALLISTIC)),
            new FilteringButton(WeaponType.MISSILE.getDisplayName(), new WeaponSlotIsType(WeaponType.MISSILE)),
            new FilteringButton(WeaponType.ENERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.ENERGY)),
            new FilteringButton(WeaponType.HYBRID.getDisplayName(), new WeaponSlotIsType(WeaponType.HYBRID)),
            new FilteringButton(WeaponType.SYNERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.SYNERGY)),
            new FilteringButton(WeaponType.COMPOSITE.getDisplayName(), new WeaponSlotIsType(WeaponType.COMPOSITE)),
            new FilteringButton(WeaponType.UNIVERSAL.getDisplayName(), new WeaponSlotIsType(WeaponType.UNIVERSAL)),
            new FilteringButton(WeaponType.BUILT_IN.getDisplayName(), new WeaponSlotIsType(WeaponType.BUILT_IN)),
        };
    }

    private FilteringButton[] createBuiltIns() {
        List<FilteringButton> builtInList = new LinkedList<>();
        builtInList.add(
            new FilteringButton(L10n.get(CommonL10n.NONE), new ShipHullNoBuiltIns(L10n.get(CommonL10n.NONE)), "None")
        );
        for (HullModSpecAPI builtIn : shipProvider.getBuiltIns()) {
            builtInList.add(
                new FilteringButton(builtIn.getDisplayName(), new ShipHullHasBuiltIn(builtIn), builtIn.getId())
            );
        }
        return builtInList.toArray(new FilteringButton[] {});
    }

    private FilteringButton[] createDMods() {
        List<FilteringButton> dMods = new LinkedList<>();
        dMods.add(new FilteringButton(CommonL10n.NONE, new FleetMemberPristine(L10n.get(CommonL10n.NONE))));
        for (HullModSpecAPI dMod : shipProvider.getDMods()) {
            dMods.add(new FilteringButton(dMod.getDisplayName(), new FleetMemberHasDMod(dMod), false));
        }
        return dMods.toArray(new FilteringButton[] {});
    }

    private Set<Filter> getCommonFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addToFilters(filters, classSizes, L10n.get(QueryL10n.CLASS_SIZE), true);
        addToFilters(filters, mountSizes, L10n.get(QueryL10n.MOUNT_SIZE), true);
        addToFilters(filters, mountTypes, L10n.get(QueryL10n.MOUNT_TYPE), true);
        addToFilters(filters, mountBays, L10n.get(QueryL10n.FIGHTER_BAYS), true);
        addToFilters(filters, manufacturers, L10n.get(QueryL10n.MANUFACTURERS), true);
        return filters;
    }

    private void prepareBuiltIns() {
        Set<Filter> filters = getCommonFilters();
        Set<String> hullModIds = shipProvider.getBuiltInIds(filters);
        hullModIds.add("None");
        for (FilteringButton button : builtIns) {
            button.updateVisibility(hullModIds);
        }
    }

    private List<FleetMemberAPI> getShips(Set<Filter> filters) {
        return shipProvider.getMatching(filters);
    }
}
