package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotOther extends CargoStackFilter {

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        return (object.isCommodityStack() || object.isWeaponStack() || object.isFighterWingStack());
    }
}
