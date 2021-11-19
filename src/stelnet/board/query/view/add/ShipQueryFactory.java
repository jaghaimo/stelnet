package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.provider.ShipProvider;
import stelnet.filter.Filter;
import stelnet.filter.ShipHullIsManufacturer;
import stelnet.filter.ShipHullIsSize;
import stelnet.filter.WeaponSlotIsSize;
import stelnet.filter.WeaponSlotIsType;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowShips;
import uilib.property.Size;

public class ShipQueryFactory extends QueryFactory {

    private final ShipProvider shipProvider = new ShipProvider(this);
    private final FilteringButton[] classSizes = createClassSizes();
    private final FilteringButton[] mountSizes = createMountSizes();
    private final FilteringButton[] mountTypes = createMountTypes();
    private final FilteringButton[] manufacturers = createManufacturers();

    @Override
    public Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addToFilters(filters, classSizes, L10n.get(QueryL10n.CLASS_SIZE), true);
        addToFilters(filters, mountSizes, L10n.get(QueryL10n.MOUNT_SIZE), false);
        addToFilters(filters, mountTypes, L10n.get(QueryL10n.MOUNT_TYPE), false);
        addToFilters(filters, manufacturers, L10n.get(QueryL10n.MANUFACTURERS), false);
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
    protected List<Renderable> getFinalComponents() {
        Set<Filter> filters = getFilters();
        return Arrays.<Renderable>asList(
            new FindMatchingButton(this, L10n.get(CommonL10n.SHIPS)),
            new SelectAndFindButton(this, L10n.get(CommonL10n.SHIPS), getShips(filters))
        );
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.CLASS_SIZE, Arrays.<Renderable>asList(classSizes));
        addSection(elements, QueryL10n.WEAPON_MOUNTS);
        addLabeledGroup(elements, QueryL10n.MOUNT_TYPE, Arrays.<Renderable>asList(mountTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_SIZE, Arrays.<Renderable>asList(mountSizes));
        addSection(elements, QueryL10n.MANUFACTURERS);
        addUnlabelledGroup(elements, Arrays.<Renderable>asList(manufacturers));
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
            manufacturers.add(new FilteringButton(manufacturer, new ShipHullIsManufacturer(manufacturer)));
        }
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

    private List<FleetMemberAPI> getShips(Set<Filter> filters) {
        return shipProvider.getMatching(filters);
    }
}
