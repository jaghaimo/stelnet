package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;

import stelnet.market.DialogOption;

public class IsWeaponType implements CargoStackFilter {

    private DialogOption option;

    public IsWeaponType(DialogOption o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isWeaponStack()) {
            return false;
        }

        WeaponType weaponType = c.getWeaponSpecIfWeapon().getType();

        switch (option) {
        case WEAPON_TYPE_BALLISTIC:
            return weaponType.equals(WeaponType.BALLISTIC);

        case WEAPON_TYPE_ENERGY:
            return weaponType.equals(WeaponType.ENERGY);

        case WEAPON_TYPE_MISSILE:
            return weaponType.equals(WeaponType.MISSILE);

        default:
            return true;
        }
    }
}
