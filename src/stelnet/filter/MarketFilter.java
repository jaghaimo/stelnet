package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public abstract class MarketFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof MarketAPI) {
            return acceptMarket((MarketAPI) object);
        }
        return super.accept(object);
    }

    protected abstract boolean acceptMarket(MarketAPI market);
}
