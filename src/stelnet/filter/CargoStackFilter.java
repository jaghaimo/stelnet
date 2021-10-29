package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public abstract class CargoStackFilter extends Filter {

    @Override
    public abstract boolean accept(CargoStackAPI cargoStack);
}
