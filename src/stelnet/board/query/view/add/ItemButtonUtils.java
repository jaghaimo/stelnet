package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WingRole;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ItemQueryProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.CargoStackIsManufacturer;
import stelnet.filter.CargoStackIsType;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.Filter;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import stelnet.util.L10n;

public enum ItemButtonUtils {
    ITEM_TYPE_WEAPONS("WEAPONS", new CargoStackIsType(Type.WEAPON)),
    ITEM_TYPE_FIGHTER_WINGS("FIGHTER_WINGS", new CargoStackIsType(Type.FIGHTER)),
    ITEM_TYPE_MODSPECS("MODSPECS", new CargoStackIsType(Type.MODSPEC)),
    DAMAGE_TYPE_KINETIC(DamageType.KINETIC),
    DAMAGE_TYPE_HIGH_EXPLOSIVE(DamageType.HIGH_EXPLOSIVE),
    DAMAGE_TYPE_FRAGMENTATION(DamageType.FRAGMENTATION),
    DAMAGE_TYPE_ENERGY(DamageType.ENERGY),
    WEAPON_SIZE_SMALL(WeaponSize.SMALL),
    WEAPON_SIZE_MEDIUM(WeaponSize.MEDIUM),
    WEAPON_SIZE_LARGE(WeaponSize.LARGE),
    WEAPON_TYPE_BALLISTIC(WeaponType.BALLISTIC),
    WEAPON_TYPE_MISSILE(WeaponType.MISSILE),
    WEAPON_TYPE_ENERGY(WeaponType.ENERGY),
    WEAPON_TYPE_HYBRID(WeaponType.HYBRID),
    WEAPON_TYPE_SYNERGY(WeaponType.SYNERGY),
    WEAPON_TYPE_COMPOSITE(WeaponType.COMPOSITE),
    WEAPON_TYPE_UNIVERSAL(WeaponType.UNIVERSAL),
    WING_ROLE_ASSAULT(WingRole.ASSAULT),
    WING_ROLE_BOMBER(WingRole.BOMBER),
    WING_ROLE_FIGHTER(WingRole.FIGHTER),
    WING_ROLE_INTERCEPTOR(WingRole.INTERCEPTOR),
    WING_ROLE_SUPPORT(WingRole.SUPPORT);

    public static FilteringButton[] createItemTypes() {
        return new FilteringButton[] {
            ITEM_TYPE_WEAPONS.getButton(),
            ITEM_TYPE_FIGHTER_WINGS.getButton(),
            ITEM_TYPE_MODSPECS.getButton(),
        };
    }

    public static FilteringButton[] createDamageType() {
        return new FilteringButton[] {
            DAMAGE_TYPE_KINETIC.getButton(),
            DAMAGE_TYPE_HIGH_EXPLOSIVE.getButton(),
            DAMAGE_TYPE_FRAGMENTATION.getButton(),
            DAMAGE_TYPE_ENERGY.getButton(),
        };
    }

    public static FilteringButton[] createWeaponMountSize() {
        return new FilteringButton[] {
            WEAPON_SIZE_SMALL.getButton(),
            WEAPON_SIZE_MEDIUM.getButton(),
            WEAPON_SIZE_LARGE.getButton(),
        };
    }

    public static FilteringButton[] createWeaponMountType() {
        return new FilteringButton[] {
            WEAPON_TYPE_BALLISTIC.getButton(),
            WEAPON_TYPE_MISSILE.getButton(),
            WEAPON_TYPE_ENERGY.getButton(),
            WEAPON_TYPE_HYBRID.getButton(),
            WEAPON_TYPE_SYNERGY.getButton(),
            WEAPON_TYPE_COMPOSITE.getButton(),
            WEAPON_TYPE_UNIVERSAL.getButton(),
        };
    }

    public static FilteringButton[] createWingRole() {
        return new FilteringButton[] {
            WING_ROLE_ASSAULT.getButton(),
            WING_ROLE_BOMBER.getButton(),
            WING_ROLE_FIGHTER.getButton(),
            WING_ROLE_INTERCEPTOR.getButton(),
            WING_ROLE_SUPPORT.getButton(),
        };
    }

    public static FilteringButton[] createManufacturers(final ItemQueryProvider provider) {
        final List<FilteringButton> manufacturers = new LinkedList<>();
        for (final String manufacturer : provider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new CargoStackIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }

    private final String title;

    private final Filter filter;

    private ItemButtonUtils(final String key, final Filter filter) {
        this.title = L10n.common(key);
        this.filter = filter;
    }

    private ItemButtonUtils(final DamageType damageType) {
        this(damageType.getDisplayName(), new WeaponIsDamage(damageType));
    }

    private ItemButtonUtils(final WeaponSize weaponSize) {
        this(weaponSize.getDisplayName(), new WeaponIsSize(weaponSize));
    }

    private ItemButtonUtils(final WeaponType weaponType) {
        this(weaponType.getDisplayName(), new WeaponIsType(weaponType));
    }

    private ItemButtonUtils(final WingRole wingRole) {
        this(L10n.common("ROLE_" + wingRole.name()), new CargoStackWingIsRole(wingRole));
    }

    public FilteringButton getButton() {
        return new FilteringButton(title, filter);
    }
}
