package stelnet.util;

import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;

public class Factory {

    public static FleetMemberAPI createShip(String variant) {
        return getFactory().createFleetMember(FleetMemberType.SHIP, variant);
    }

    public static CargoStackAPI createWeapon(String id) {
        return getFactory().createCargoStack(CargoItemType.WEAPONS, id, null);
    }

    private static FactoryAPI getFactory() {
        return Global.getFactory();
    }
}
