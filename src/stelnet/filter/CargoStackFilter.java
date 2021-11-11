package stelnet.filter;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;

public abstract class CargoStackFilter extends MarketFilter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof CargoStackAPI) {
            return acceptCargoStack((CargoStackAPI) object);
        }
        return super.accept(object);
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            if (acceptSubmarket(submarket)) {
                return true;
            }
        }
        return false;
    }

    protected boolean acceptSubmarket(SubmarketAPI submarket) {
        for (CargoStackAPI cargoStack : submarket.getCargo().getStacksCopy()) {
            if (acceptCargoStack(cargoStack)) {
                return true;
            }
        }
        return false;
    }

    protected abstract boolean acceptCargoStack(CargoStackAPI cargoStack);
}
