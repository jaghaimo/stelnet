package stelnet.util;

import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoAPI.CargoItemType;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import java.util.List;

public class FactoryUtils {

    public static CargoAPI createCargo() {
        return getFactory().createCargo(true);
    }

    public static CargoAPI createCargo(List<CargoStackAPI> stacks) {
        CargoAPI cargo = createCargo();
        for (CargoStackAPI stack : stacks) {
            cargo.addFromStack(stack);
        }
        return cargo;
    }

    public static CargoStackAPI createFighterItem(String variant) {
        return getFactory().createCargoStack(CargoItemType.FIGHTER_CHIP, variant, null);
    }

    public static FleetMemberAPI createFleetMember(String variant) {
        return getFactory().createFleetMember(FleetMemberType.SHIP, variant);
    }

    public static CargoStackAPI createModspecItem(String modspecId) {
        SpecialItemData specialItemdata = new SpecialItemData(Items.MODSPEC, modspecId);
        return getFactory().createCargoStack(CargoItemType.SPECIAL, specialItemdata, null);
    }

    public static CargoStackAPI createWeaponItem(String id) {
        return getFactory().createCargoStack(CargoItemType.WEAPONS, id, null);
    }

    private static FactoryAPI getFactory() {
        return Global.getFactory();
    }
}
