package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotOther extends CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return (object.isCommodityStack() || object.isWeaponStack() || object.isFighterWingStack());
    }
}
