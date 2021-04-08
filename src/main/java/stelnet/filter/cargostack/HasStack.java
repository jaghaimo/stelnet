package stelnet.filter.cargostack;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class HasStack implements CargoStackFilter {

    private CargoStackAPI cargoStack;

    public HasStack(CargoStackAPI c) {
        cargoStack = c;
    }

    public boolean accept(CargoStackAPI c) {
        return cargoStack.getDisplayName().equals(c.getDisplayName());
    }
}
