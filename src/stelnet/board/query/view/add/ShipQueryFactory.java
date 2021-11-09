package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ShipProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOrFilter;
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

    private transient ShipProvider shipProvider = new ShipProvider();
    private transient ShipButton[] classSizes = createClassSizes();
    private transient ShipButton[] mountSizes = createMountSizes();
    private transient ShipButton[] mountTypes = createMountTypes();
    private transient ShipButton[] manufacturers = createManufacturers();

    public void readResolve() {
        shipProvider = new ShipProvider();
        classSizes = createClassSizes();
        mountSizes = createMountSizes();
        mountTypes = createMountTypes();
        manufacturers = createManufacturers();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.CLASS_SIZE, Arrays.<Renderable>asList(classSizes));
        addSection(elements, QueryL10n.WEAPON_MOUNTS);
        addLabeledGroup(elements, QueryL10n.MOUNT_SIZE, Arrays.<Renderable>asList(mountSizes));
        addLabeledGroup(elements, QueryL10n.MOUNT_TYPE, Arrays.<Renderable>asList(mountTypes));
        addSection(elements, QueryL10n.MANUFACTURERS);
        addUnlabelledGroup(elements, Arrays.<Renderable>asList(manufacturers));
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        List<Filter> filters = getFilters();
        return new ShowShips(shipProvider.getShips(filters), "No matching ships found.", size);
    }

    private List<Filter> getFilters() {
        List<Filter> filters = new LinkedList<>();
        filters.add(new LogicalOrFilter(getFilters(classSizes)));
        filters.add(new LogicalOrFilter(getFilters(mountSizes)));
        filters.add(new LogicalOrFilter(getFilters(mountTypes)));
        filters.add(new LogicalOrFilter(getFilters(manufacturers)));
        return filters;
    }

    private ShipButton[] createClassSizes() {
        return new ShipButton[] {
            new ShipButton(L10n.get(HullSize.FRIGATE), new ShipHullIsSize(HullSize.FRIGATE)),
            new ShipButton(L10n.get(HullSize.DESTROYER), new ShipHullIsSize(HullSize.DESTROYER)),
            new ShipButton(L10n.get(HullSize.CRUISER), new ShipHullIsSize(HullSize.CRUISER)),
            new ShipButton(L10n.get(HullSize.CAPITAL_SHIP), new ShipHullIsSize(HullSize.CAPITAL_SHIP)),
        };
    }

    private ShipButton[] createManufacturers() {
        List<ShipButton> manufacturers = new LinkedList<>();
        for (String manufacturer : shipProvider.getManufacturers()) {
            manufacturers.add(new ShipButton(manufacturer, new ShipHullIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new ShipButton[] {});
    }

    private ShipButton[] createMountSizes() {
        return new ShipButton[] {
            new ShipButton(WeaponSize.SMALL.getDisplayName(), new WeaponSlotIsSize(WeaponSize.SMALL)),
            new ShipButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponSlotIsSize(WeaponSize.MEDIUM)),
            new ShipButton(WeaponSize.LARGE.getDisplayName(), new WeaponSlotIsSize(WeaponSize.LARGE)),
        };
    }

    private ShipButton[] createMountTypes() {
        return new ShipButton[] {
            new ShipButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponSlotIsType(WeaponType.BALLISTIC)),
            new ShipButton(WeaponType.MISSILE.getDisplayName(), new WeaponSlotIsType(WeaponType.MISSILE)),
            new ShipButton(WeaponType.ENERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.ENERGY)),
            new ShipButton(WeaponType.HYBRID.getDisplayName(), new WeaponSlotIsType(WeaponType.HYBRID)),
            new ShipButton(WeaponType.SYNERGY.getDisplayName(), new WeaponSlotIsType(WeaponType.SYNERGY)),
            new ShipButton(WeaponType.COMPOSITE.getDisplayName(), new WeaponSlotIsType(WeaponType.COMPOSITE)),
            new ShipButton(WeaponType.UNIVERSAL.getDisplayName(), new WeaponSlotIsType(WeaponType.UNIVERSAL)),
            new ShipButton(WeaponType.BUILT_IN.getDisplayName(), new WeaponSlotIsType(WeaponType.BUILT_IN)),
        };
    }
}
