package stelnet.market.intel.provider;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.submarket.HasCargoStack;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.market.intel.subject.CargoSubject;
import stelnet.market.intel.subject.IntelSubject;

public class CargoIntelProvider extends SubmarketProvider {

    private final CargoStackAPI cargoStack;

    public CargoIntelProvider(CargoStackAPI c) {
        cargoStack = c;
    }

    @Override
    protected SubmarketFilter getFilter() {
        return new HasCargoStack(cargoStack);
    }

    @Override
    protected IntelSubject getSubject(MarketAPI market) {
        return new CargoSubject(cargoStack, market);
    }
}
