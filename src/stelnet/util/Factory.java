package stelnet.util;

import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;

public class Factory {

    public static CargoStackAPI createFighterItem(FighterWingSpecAPI fighterWing) {
        return getFactory().createCargoStack(CargoItemType.FIGHTER_CHIP, fighterWing, null);
    }

    public static FleetMemberAPI createFleetMember(String variant) {
        return getFactory().createFleetMember(FleetMemberType.SHIP, variant);
    }

    public static CargoStackAPI createModspecItem(SpecialItemSpecAPI specialItem) {
        return getFactory().createCargoStack(CargoItemType.SPECIAL, specialItem, null);
    }

    public static CargoStackAPI createWeaponItem(String id) {
        return getFactory().createCargoStack(CargoItemType.WEAPONS, id, null);
    }

    private static FactoryAPI getFactory() {
        return Global.getFactory();
    }
}
