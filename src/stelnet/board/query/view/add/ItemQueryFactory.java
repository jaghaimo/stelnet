package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WingRole;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ItemProvider;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.CargoStackIsManufacturer;
import stelnet.filter.CargoStackIsType;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.filter.CargoStackKnownModspec;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowCargo;
import uilib.property.Size;

public class ItemQueryFactory extends QueryFactory {

    private transient ItemProvider itemProvider = new ItemProvider();
    private transient ItemButton[] itemTypes = createItemTypes();
    private transient ItemButton[] manufacturers = createManufacturers();
    private transient ItemButton[] weaponDamageTypes = createDamageType();
    private transient ItemButton[] weaponMountSizes = createWeaponMountSize();
    private transient ItemButton[] weaponMountTypes = createWeaponMountType();
    private transient ItemButton[] wingRoles = createWingRole();

    public void readResolve() {
        itemProvider = new ItemProvider();
        itemTypes = createItemTypes();
        manufacturers = createManufacturers();
        weaponDamageTypes = createDamageType();
        weaponMountSizes = createWeaponMountSize();
        weaponMountTypes = createWeaponMountType();
        wingRoles = createWingRole();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.ITEM_TYPES, Arrays.<Renderable>asList(itemTypes));
        addSection(elements, CommonL10n.WEAPONS);
        addLabeledGroup(elements, QueryL10n.DAMAGE_TYPE, Arrays.<Renderable>asList(weaponDamageTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_TYPE, Arrays.<Renderable>asList(weaponMountTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_SIZE, Arrays.<Renderable>asList(weaponMountSizes));
        addSection(elements, CommonL10n.FIGHTER_WINGS);
        addLabeledGroup(elements, QueryL10n.WING_ROLES, Arrays.<Renderable>asList(wingRoles));
        addSection(elements, QueryL10n.MANUFACTURERS);
        addUnlabelledGroup(elements, Arrays.<Renderable>asList(manufacturers));
        return elements;
    }

    @Override
    protected Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        filters.add(new LogicalOr(getFilters(itemTypes)));
        filters.add(new LogicalOr(getFilters(manufacturers)));
        filters.add(new LogicalOr(getFilters(weaponDamageTypes)));
        filters.add(new LogicalOr(getFilters(weaponMountSizes)));
        filters.add(new LogicalOr(getFilters(weaponMountTypes)));
        filters.add(new LogicalOr(getFilters(wingRoles)));
        filters.add(new LogicalNot(new CargoStackKnownModspec()));
        return filters;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        Set<Filter> filters = getFilters();
        return new ShowCargo(itemProvider.getMatching(filters), "No matching items found.", size);
    }

    @Override
    protected QueryProvider getProvider() {
        return itemProvider;
    }

    private ItemButton[] createItemTypes() {
        return new ItemButton[] {
            new ItemButton(L10n.get(CommonL10n.WEAPONS), new CargoStackIsType(Type.WEAPON)),
            new ItemButton(L10n.get(CommonL10n.FIGHTER_WINGS), new CargoStackIsType(Type.FIGHTER)),
            new ItemButton(L10n.get(CommonL10n.MODSPECS), new CargoStackIsType(Type.MODSPEC)),
        };
    }

    private ItemButton[] createDamageType() {
        return new ItemButton[] {
            new ItemButton(DamageType.KINETIC.getDisplayName(), new WeaponIsDamage(DamageType.KINETIC)),
            new ItemButton(DamageType.HIGH_EXPLOSIVE.getDisplayName(), new WeaponIsDamage(DamageType.HIGH_EXPLOSIVE)),
            new ItemButton(DamageType.FRAGMENTATION.getDisplayName(), new WeaponIsDamage(DamageType.FRAGMENTATION)),
            new ItemButton(DamageType.ENERGY.getDisplayName(), new WeaponIsDamage(DamageType.ENERGY)),
        };
    }

    private ItemButton[] createWeaponMountSize() {
        return new ItemButton[] {
            new ItemButton(WeaponSize.SMALL.getDisplayName(), new WeaponIsSize(WeaponSize.SMALL)),
            new ItemButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponIsSize(WeaponSize.MEDIUM)),
            new ItemButton(WeaponSize.LARGE.getDisplayName(), new WeaponIsSize(WeaponSize.LARGE)),
        };
    }

    private ItemButton[] createWeaponMountType() {
        return new ItemButton[] {
            new ItemButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponIsType(WeaponType.BALLISTIC)),
            new ItemButton(WeaponType.MISSILE.getDisplayName(), new WeaponIsType(WeaponType.MISSILE)),
            new ItemButton(WeaponType.ENERGY.getDisplayName(), new WeaponIsType(WeaponType.ENERGY)),
            new ItemButton(WeaponType.HYBRID.getDisplayName(), new WeaponIsType(WeaponType.HYBRID)),
            new ItemButton(WeaponType.SYNERGY.getDisplayName(), new WeaponIsType(WeaponType.SYNERGY)),
            new ItemButton(WeaponType.COMPOSITE.getDisplayName(), new WeaponIsType(WeaponType.COMPOSITE)),
            new ItemButton(WeaponType.UNIVERSAL.getDisplayName(), new WeaponIsType(WeaponType.UNIVERSAL)),
        };
    }

    private ItemButton[] createWingRole() {
        return new ItemButton[] {
            new ItemButton(L10n.get(WingRole.ASSAULT), new CargoStackWingIsRole(WingRole.ASSAULT)),
            new ItemButton(L10n.get(WingRole.BOMBER), new CargoStackWingIsRole(WingRole.BOMBER)),
            new ItemButton(L10n.get(WingRole.FIGHTER), new CargoStackWingIsRole(WingRole.FIGHTER)),
            new ItemButton(L10n.get(WingRole.INTERCEPTOR), new CargoStackWingIsRole(WingRole.INTERCEPTOR)),
            new ItemButton(L10n.get(WingRole.SUPPORT), new CargoStackWingIsRole(WingRole.SUPPORT)),
        };
    }

    private ItemButton[] createManufacturers() {
        List<ItemButton> manufacturers = new LinkedList<>();
        for (String manufacturer : itemProvider.getManufacturers()) {
            manufacturers.add(new ItemButton(manufacturer, new CargoStackIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new ItemButton[] {});
    }
}
