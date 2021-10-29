package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotWeapon extends CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return !object.isWeaponStack();
    }
}
