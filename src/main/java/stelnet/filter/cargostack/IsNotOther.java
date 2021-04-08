package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class IsNotOther implements CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return object.isCommodityStack() || object.isWeaponStack() || object.isFighterWingStack();
    }
}
