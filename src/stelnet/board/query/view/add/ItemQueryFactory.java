package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WingRole;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.ItemProvider;
import stelnet.filter.CargoStackIsManufacturer;
import stelnet.filter.CargoStackKnownModspec;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNotFilter;
import stelnet.filter.LogicalOrFilter;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import stelnet.util.L10n;
import uilib.Cargo;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ItemQueryFactory extends QueryFactory {

    private transient ItemProvider itemProvider = new ItemProvider();
    private transient ItemButton[] manufacturers = createManufacturers();
    private transient ItemButton[] weaponDamageTypes = createWeaponDamageType();
    private transient ItemButton[] weaponMountSizes = createWeaponMountSize();
    private transient ItemButton[] weaponMountTypes = createWeaponMountType();
    private transient ItemButton[] wingRoles = createWingRole();

    public void readResolve() {
        itemProvider = new ItemProvider();
        manufacturers = createManufacturers();
        weaponDamageTypes = createWeaponDamageType();
        weaponMountSizes = createWeaponMountSize();
        weaponMountTypes = createWeaponMountType();
        wingRoles = createWingRole();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        addSection(elements, CommonL10n.WEAPONS);
        addLabeledGroup(elements, QueryL10n.WEAPON_DAMAGE, Arrays.<Renderable>asList(weaponDamageTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_TYPE, Arrays.<Renderable>asList(weaponMountTypes));
        addLabeledGroup(elements, QueryL10n.MOUNT_SIZE, Arrays.<Renderable>asList(weaponMountSizes));
        addSection(elements, CommonL10n.FIGHTER_WINGS);
        addLabeledGroup(elements, QueryL10n.WING_ROLES, Arrays.<Renderable>asList(wingRoles));
        addSection(elements, QueryL10n.MANUFACTURERS);
        addUnlabelledGroup(elements, Arrays.<Renderable>asList(manufacturers));
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        List<Filter> filters = getFilters();
        CargoAPI cargo = itemProvider.getItems(filters);
        return new Cargo(cargo, "No matching items found.", size);
    }

    private List<Filter> getFilters() {
        List<Filter> filters = new LinkedList<>();
        filters.add(new LogicalOrFilter(getFilters(manufacturers)));
        filters.add(new LogicalOrFilter(getFilters(weaponDamageTypes)));
        filters.add(new LogicalOrFilter(getFilters(weaponMountSizes)));
        filters.add(new LogicalOrFilter(getFilters(weaponMountTypes)));
        filters.add(new LogicalOrFilter(getFilters(wingRoles)));
        filters.add(new LogicalNotFilter(new CargoStackKnownModspec()));
        return filters;
    }

    private ItemButton[] createWeaponDamageType() {
        return new ItemButton[] {
            new ItemButton(DamageType.KINETIC.getDisplayName(), new WeaponIsDamage(DamageType.KINETIC)),
            new ItemButton(DamageType.HIGH_EXPLOSIVE.getDisplayName(), new WeaponIsDamage(DamageType.HIGH_EXPLOSIVE)),
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
