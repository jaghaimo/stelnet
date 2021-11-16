package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
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
import stelnet.filter.CargoStackNotKnownModspec;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.Filter;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.ShowCargo;
import uilib.property.Size;

public class ItemQueryFactory extends QueryFactory {

    private final ItemProvider itemProvider = new ItemProvider(this);
    private final ItemButton[] itemTypes = createItemTypes();
    private final ItemButton[] manufacturers = createManufacturers();
    private final ItemButton[] weaponDamageTypes = createDamageType();
    private final ItemButton[] weaponMountSizes = createWeaponMountSize();
    private final ItemButton[] weaponMountTypes = createWeaponMountType();
    private final ItemButton[] wingRoles = createWingRole();

    @Override
    public RenderableComponent getPreview(Size size) {
        Set<Filter> filters = getFilters();
        CargoAPI cargo = CargoUtils.makeCargoFromStacks(itemProvider.getMatching(filters));
        return new ShowCargo(cargo, "Matching items", "No matching items found.", size);
    }

    @Override
    protected Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        addToFilters(filters, itemTypes, L10n.get(QueryL10n.ITEM_TYPES), true);
        addToFilters(filters, manufacturers, L10n.get(QueryL10n.MANUFACTURERS), false);
        addToFilters(filters, weaponDamageTypes, L10n.get(QueryL10n.DAMAGE_TYPE), false);
        addToFilters(filters, weaponMountSizes, L10n.get(QueryL10n.MOUNT_SIZE), false);
        addToFilters(filters, weaponMountTypes, L10n.get(QueryL10n.MOUNT_TYPE), false);
        addToFilters(filters, wingRoles, L10n.get(QueryL10n.WING_ROLES), false);
        filters.add(new CargoStackNotKnownModspec());
        return filters;
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
