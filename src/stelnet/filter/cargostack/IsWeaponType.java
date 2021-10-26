package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.combat.WeaponAPI.WeaponType;

public class IsWeaponType implements CargoStackFilter {

    // TODO: Temporary implementation
    public enum Type {
        BALLISTIC,
        ENERGY,
        MISSILE,
    }

    private final Type option;

    public IsWeaponType(Type o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isWeaponStack()) {
            return false;
        }

        WeaponType weaponType = c.getWeaponSpecIfWeapon().getType();

        switch (option) {
            case BALLISTIC:
                return weaponType.equals(WeaponType.BALLISTIC);
            case ENERGY:
                return weaponType.equals(WeaponType.ENERGY);
            case MISSILE:
                return weaponType.equals(WeaponType.MISSILE);
            default:
                return true;
        }
    }
}
