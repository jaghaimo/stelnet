package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;

public class CargoStackIsNotFighterWingRole extends CargoStackFilter {

    private final WingRole wingRole;

    public CargoStackIsNotFighterWingRole(WingRole wingRole) {
        this.wingRole = wingRole;
    }

    @Override
    public boolean accept(CargoStackAPI object) {
        if (!object.isFighterWingStack()) {
            return true;
        }
        return object.getFighterWingSpecIfWing().getRole() != wingRole;
    }
}
