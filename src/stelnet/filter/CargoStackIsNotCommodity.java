package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;

public class CargoStackIsNotCommodity extends CargoStackFilter {

    @Override
    public boolean accept(CargoStackAPI object) {
        return !object.isCommodityStack();
    }
}
