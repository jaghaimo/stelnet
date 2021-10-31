package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoStackIsType extends CargoStackFilter {

    public static enum Type {
        WEAPON,
        FIGHTER,
        MODSPEC,
        BLUEPRINT,
    }

    private final Type type;

    @Override
    protected boolean acceptCargoStack(CargoStackAPI c) {
        switch (type) {
            case WEAPON:
                return c.isWeaponStack();
            case FIGHTER:
                return c.isFighterWingStack();
            case MODSPEC:
                return isModspec(c);
            case BLUEPRINT:
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
