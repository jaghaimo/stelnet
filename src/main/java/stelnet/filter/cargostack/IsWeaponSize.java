package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

public class IsWeaponSize implements CargoStackFilter {

    // TODO: Temporary implementation
    public enum Size {
        SIZE_SMALL, SIZE_MEDIUM, SIZE_LARGE;
    }

    private final Size option;

    public IsWeaponSize(Size o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isWeaponStack()) {
            return false;
        }

        WeaponSize weaponSize = c.getWeaponSpecIfWeapon().getSize();

        switch (option) {
            case SIZE_SMALL:
                return weaponSize.equals(WeaponSize.SMALL);

            case SIZE_MEDIUM:
                return weaponSize.equals(WeaponSize.MEDIUM);

            case SIZE_LARGE:
                return weaponSize.equals(WeaponSize.LARGE);

            default:
                return true;
        }
    }
}
