package stelnet.board.query.view.add;

import com.fs.starfarer.api.combat.DamageType;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;
import com.fs.starfarer.api.loading.WingRole;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.provider.ItemProvider;
import stelnet.filter.CargoStackIsManufacturer;
import stelnet.filter.CargoStackIsType;
import stelnet.filter.CargoStackIsType.Type;
import stelnet.filter.CargoStackWingIsRole;
import stelnet.filter.WeaponIsDamage;
import stelnet.filter.WeaponIsSize;
import stelnet.filter.WeaponIsType;
import stelnet.util.L10n;

public class ItemButtonUtils {

    public static FilteringButton[] createItemTypes() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(CommonL10n.WEAPONS), new CargoStackIsType(Type.WEAPON)),
            new FilteringButton(L10n.get(CommonL10n.FIGHTER_WINGS), new CargoStackIsType(Type.FIGHTER)),
            new FilteringButton(L10n.get(CommonL10n.MODSPECS), new CargoStackIsType(Type.MODSPEC)),
        };
    }

    public static FilteringButton[] createDamageType() {
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

    public static FilteringButton[] createWeaponMountSize() {
        return new FilteringButton[] {
            new FilteringButton(WeaponSize.SMALL.getDisplayName(), new WeaponIsSize(WeaponSize.SMALL)),
            new FilteringButton(WeaponSize.MEDIUM.getDisplayName(), new WeaponIsSize(WeaponSize.MEDIUM)),
            new FilteringButton(WeaponSize.LARGE.getDisplayName(), new WeaponIsSize(WeaponSize.LARGE)),
        };
    }

    public static FilteringButton[] createWeaponMountType() {
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

    public static FilteringButton[] createWingRole() {
        return new FilteringButton[] {
            new FilteringButton(L10n.get(WingRole.ASSAULT), new CargoStackWingIsRole(WingRole.ASSAULT)),
            new FilteringButton(L10n.get(WingRole.BOMBER), new CargoStackWingIsRole(WingRole.BOMBER)),
            new FilteringButton(L10n.get(WingRole.FIGHTER), new CargoStackWingIsRole(WingRole.FIGHTER)),
            new FilteringButton(L10n.get(WingRole.INTERCEPTOR), new CargoStackWingIsRole(WingRole.INTERCEPTOR)),
            new FilteringButton(L10n.get(WingRole.SUPPORT), new CargoStackWingIsRole(WingRole.SUPPORT)),
        };
    }

    public static FilteringButton[] createManufacturers(ItemProvider provider) {
        List<FilteringButton> manufacturers = new LinkedList<>();
        for (String manufacturer : provider.getManufacturers()) {
            manufacturers.add(new FilteringButton(manufacturer, new CargoStackIsManufacturer(manufacturer)));
        }
        return manufacturers.toArray(new FilteringButton[] {});
    }
}
