package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponSize;

public class CargoStackIsNotMountSize extends CargoStackFilter {

    private final WeaponSize weaponSize;

    public CargoStackIsNotMountSize(WeaponSize weaponSize) {
        this.weaponSize = weaponSize;
    }

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        if (!object.isWeaponStack()) {
            return true;
        }
        return object.getWeaponSpecIfWeapon().getSize() != weaponSize;
    }
}
