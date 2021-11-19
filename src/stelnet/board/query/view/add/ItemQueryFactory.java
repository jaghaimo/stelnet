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
    private final FilteringButton[] itemTypes = createItemTypes();
    private final FilteringButton[] manufacturers = createManufacturers();
    private final FilteringButton[] weaponDamageTypes = createDamageType();
    private final FilteringButton[] weaponMountSizes = createWeaponMountSize();
    private final FilteringButton[] weaponMountTypes = createWeaponMountType();
    private final FilteringButton[] wingRoles = createWingRole();

    @Override
    public Set<Filter> getFilters() {
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
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return new ShowCargo(
            getCargo(filters),
            L10n.get(QueryL10n.MATCHING_ITEMS),
            L10n.get(QueryL10n.NO_MATCHING_ITEMS),
            size
        );
    }

    @Override
    public QueryProvider getProvider() {
        return itemProvider;
    }

    @Override
    protected List<Renderable> getFinalComponents() {
        Set<Filter> filters = getFilters();
        return Arrays.<Renderable>asList(
            new FindMatchingButton(this),
            new SelectAndFindButton(this, getCargo(filters))
        );
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
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

    private FilteringButton[] createItemTypes() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(CommonL10n.WEAPONS), new CargoStackIsType(Type.WEAPON)),
            new FilteringButton(L10n.get(CommonL10n.FIGHTER_WINGS), new CargoStackIsType(Type.FIGHTER)),
            new FilteringButton(L10n.get(CommonL10n.MODSPECS), new CargoStackIsType(Type.MODSPEC)),
        };
    }

    private FilteringButton[] createDamageType() {
        return new FilteringButton[] {
            new FilteringButton(DamageType.KINETIC.getDisplayName(), new WeaponIsDamage(DamageType.KINETIC)),
            new FilteringButton(
                DamageType.HIGH_EXPLOSIVE.getDisplayName(),
                new WeaponIsDamage(DamageType.HIGH_EXPLOSIVE)
            ),
            new FilteringButton(
                DamageType.FRAGMENTATION.getDisplayName(),
                new WeaponIsDamage(DamageType.FRAGMENTATION)
            ),
            new FilteringButton(DamageType.ENERGY.getDisplayName(), new WeaponIsDamage(DamageType.ENERGY)),
        };
    }

    private FilteringButton[] createWeaponMountSize() {
        return new FilteringButton[] {
            new FilteringButton(WeaponSize.SMALL.getDisplayName(), new WeaponIsSize(WeaponSize.SMALL)),
            new FilteringButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponIsSize(WeaponSize.MEDIUM)),
            new FilteringButton(WeaponSize.LARGE.getDisplayName(), new WeaponIsSize(WeaponSize.LARGE)),
        };
    }

    private FilteringButton[] createWeaponMountType() {
        return new FilteringButton[] {
            new FilteringButton(WeaponType.BALLISTIC.getDisplayName(), new WeaponIsType(WeaponType.BALLISTIC)),
            new FilteringButton(WeaponType.MISSILE.getDisplayName(), new WeaponIsType(WeaponType.MISSILE)),
            new FilteringButton(WeaponType.ENERGY.getDisplayName(), new WeaponIsType(WeaponType.ENERGY)),
            new FilteringButton(WeaponType.HYBRID.getDisplayName(), new WeaponIsType(WeaponType.HYBRID)),
            new FilteringButton(WeaponType.SYNERGY.getDisplayName(), new WeaponIsType(WeaponType.SYNERGY)),
            new FilteringButton(WeaponType.COMPOSITE.getDisplayName(), new WeaponIsType(WeaponType.COMPOSITE)),
            new FilteringButton(WeaponType.UNIVERSAL.getDisplayName(), new WeaponIsType(WeaponType.UNIVERSAL)),
        };
    }

    private FilteringButton[] createWingRole() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(WingRole.ASSAULT), new CargoStackWingIsRole(WingRole.ASSAULT)),
            new FilteringButton(L10n.get(WingRole.BOMBER), new CargoStackWingIsRole(WingRole.BOMBER)),
            new FilteringButton(L10n.get(WingRole.FIGHTER), new CargoStackWingIsRole(WingRole.FIGHTER)),
            new FilteringButton(L10n.get(WingRole.INTERCEPTOR), new CargoStackWingIsRole(WingRole.INTERCEPTOR)),
            new FilteringButton(L10n.get(WingRole.SUPPORT), new CargoStackWingIsRole(WingRole.SUPPORT)),
        };
    }

    private FilteringButton[] createManufacturers() {
        List<FilteringButton> manufacturers = new LinkedList<>();
        for (String manufacturer : itemProvider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new CargoStackIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    private CargoAPI getCargo(Set<Filter> filters) {
        return CargoUtils.makeCargoFromStacks(itemProvider.getMatching(filters));
    }
}
