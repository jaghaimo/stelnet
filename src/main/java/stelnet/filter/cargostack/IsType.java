package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;

public class IsType implements CargoStackFilter {

    // TODO: Temporary implementation
    public enum Type {
        TYPE_WEAPON,
        TYPE_FIGHTER,
        TYPE_MODSPEC,
        TYPE_BLUEPRINT,
    }

    private final Type option;

    public IsType(Type o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        switch (option) {
            case TYPE_WEAPON:
                return c.isWeaponStack();
            case TYPE_FIGHTER:
                return c.isFighterWingStack();
            case TYPE_MODSPEC:
                return isModspec(c);
            case TYPE_BLUEPRINT:
                return isBlueprint(c);
            default:
                return false;
        }
    }

    private boolean isModspec(CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        return c.getSpecialDataIfSpecial().getId().equals("modspec");
    }

    private boolean isBlueprint(CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        SpecialItemSpecAPI spec = c.getSpecialItemSpecIfSpecial();
        return spec.hasTag("package_bp") || spec.getId().endsWith("_bp");
    }
}
