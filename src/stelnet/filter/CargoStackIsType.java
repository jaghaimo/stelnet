package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@RequiredArgsConstructor
public class CargoStackIsType extends CargoStackFilter {

    public static enum Type {
        COMMODITY,
        WEAPON,
        FIGHTER,
        SPECIAL,
        MODSPEC,
        BLUEPRINT,
    }

    private final Type type;

    @Override
    protected boolean acceptCargoStack(CargoStackAPI c) {
        switch (type) {
            case COMMODITY:
                return c.isCommodityStack();
            case WEAPON:
                return c.isWeaponStack();
            case FIGHTER:
                return c.isFighterWingStack();
            case SPECIAL:
                return c.isSpecialStack();
            case MODSPEC:
                return isModspec(c);
            case BLUEPRINT:
                return isBlueprint(c);
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return L10n.get(type);
    }

    protected boolean isModspec(CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        return c.getSpecialDataIfSpecial().getId().equals(Items.MODSPEC);
    }

    protected boolean isBlueprint(CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        SpecialItemSpecAPI spec = c.getSpecialItemSpecIfSpecial();
        return spec.hasTag("package_bp") || spec.getId().endsWith("_bp");
    }
}
