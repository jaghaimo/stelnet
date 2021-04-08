package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;

public class IsNotFighterWingRole implements CargoStackFilter {

    private WingRole wingRole;

    public IsNotFighterWingRole(WingRole wingRole) {
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
