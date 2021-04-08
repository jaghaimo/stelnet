package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;

import stelnet.market.DialogOption;

public class IsType implements CargoStackFilter {

    private DialogOption option;

    public IsType(DialogOption o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        switch (option) {
        case CARGO_TYPE_WEAPON:
            return c.isWeaponStack();

        case CARGO_TYPE_FIGHTER:
            return c.isFighterWingStack();

        case CARGO_TYPE_MODSPEC:
            return isModspec(c);

        case CARGO_TYPE_BLUEPRINT:
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
