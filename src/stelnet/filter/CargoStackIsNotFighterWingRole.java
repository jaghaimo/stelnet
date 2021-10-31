package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;

public class CargoStackIsNotFighterWingRole extends CargoStackFilter {

    private final WingRole wingRole;

    public CargoStackIsNotFighterWingRole(WingRole wingRole) {
        this.wingRole = wingRole;
    }

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        if (!object.isFighterWingStack()) {
            return true;
        }
        return object.getFighterWingSpecIfWing().getRole() != wingRole;
    }
}
