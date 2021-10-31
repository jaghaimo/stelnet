package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotFighterWing extends CargoStackFilter {

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        return !object.isFighterWingStack();
    }
}
