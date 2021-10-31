package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotWeapon extends CargoStackFilter {

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        return !object.isWeaponStack();
    }
}
