package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class CargoStackIsType extends CargoStackFilter {

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
    public String toString() {
        return L10n.common("STACK_" + type.name());
    }

    @Override
    protected boolean acceptCargoStack(final CargoStackAPI c) {
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

    protected boolean isModspec(final CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        return c.getSpecialDataIfSpecial().getId().equals(Items.MODSPEC);
    }

    protected boolean isBlueprint(final CargoStackAPI c) {
        if (!c.isSpecialStack()) {
            return false;
        }
        final SpecialItemSpecAPI spec = c.getSpecialItemSpecIfSpecial();
        return spec.hasTag("package_bp") || spec.getId().endsWith("_bp");
    }
}
