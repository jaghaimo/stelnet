package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotCommodity extends CargoStackFilter {

    @Override
    protected boolean acceptCargoStack(CargoStackAPI object) {
        return !object.isCommodityStack();
    }
}
