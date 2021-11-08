package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.loading.WingRole;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CargoStackIsNotFighterWingRole extends CargoStackFilter {

    private final WingRole wingRole;

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        if (!object.isFighterWingStack()) {
            return true;
        }
        return object.getFighterWingSpecIfWing().getRole() != wingRole;
    }
}
