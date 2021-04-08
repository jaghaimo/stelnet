package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

import stelnet.market.DialogOption;

public class IsWeaponSize implements CargoStackFilter {

    private DialogOption option;

    public IsWeaponSize(DialogOption o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isWeaponStack()) {
            return false;
        }

        WeaponSize weaponSize = c.getWeaponSpecIfWeapon().getSize();

        switch (option) {
        case WEAPON_SIZE_SMALL:
            return weaponSize.equals(WeaponSize.SMALL);

        case WEAPON_SIZE_MEDIUM:
            return weaponSize.equals(WeaponSize.MEDIUM);

        case WEAPON_SIZE_LARGE:
            return weaponSize.equals(WeaponSize.LARGE);

        default:
            return true;
        }
    }
}
