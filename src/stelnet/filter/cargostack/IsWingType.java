package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;

public class IsWingType implements CargoStackFilter {

    // TODO Temporary implementation
    public enum Type {
        BOMBER,
        FIGHTER,
        INTERCEPTOR,
    }

    private final Type option;

    public IsWingType(Type o) {
        option = o;
    }

    public boolean accept(CargoStackAPI c) {
        if (!c.isFighterWingStack()) {
            return false;
        }

        WingRole wingRole = c.getFighterWingSpecIfWing().getRole();

        switch (option) {
            case BOMBER:
                return wingRole.equals(WingRole.BOMBER);
            case FIGHTER:
                return wingRole.equals(WingRole.FIGHTER);
            case INTERCEPTOR:
                return wingRole.equals(WingRole.INTERCEPTOR);
            default:
                return true;
        }
    }
}
