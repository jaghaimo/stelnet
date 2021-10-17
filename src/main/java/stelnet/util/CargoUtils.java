package stelnet.util;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

public class CargoUtils {

    public static int calculateItemQuantity(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getSize();
        }
        return (int) cargoSpace;
    }

    public static int calculateShipQuantity(List<FleetMemberAPI> fleet) {
        return fleet.size();
    }

    public static CargoAPI createCargo(boolean unlimitedStacks) {
        return Global.getFactory().createCargo(unlimitedStacks);
    }

    public static CargoAPI makeCargoFromStacks(List<CargoStackAPI> cargoStacks) {
        CargoAPI cargo = createCargo(true);
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
        return cargo;
    }

    public static void replaceCargoStacks(CargoAPI cargo, List<CargoStackAPI> cargoStacks) {
        cargo.clear();
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
    }
}
