package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

public class IsNotMountSize implements CargoStackFilter {

    private WeaponSize weaponSize;

    public IsNotMountSize(WeaponSize weaponSize) {
        this.weaponSize = weaponSize;
    }

    @Override
    public boolean accept(CargoStackAPI object) {
        if (!object.isWeaponStack()) {
            return true;
        }
        return object.getWeaponSpecIfWeapon().getSize() != weaponSize;
    }
}
