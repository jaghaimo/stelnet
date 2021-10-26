package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class IsNotFighterWing implements CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return !object.isFighterWingStack();
    }
}
