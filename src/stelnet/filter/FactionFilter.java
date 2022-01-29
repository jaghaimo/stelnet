package stelnet.filter;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public abstract class FactionFilter extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof FactionAPI) {
            return acceptFaction((FactionAPI) object);
        }
        if (object instanceof MarketAPI) {
            return acceptMarket((MarketAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptMarket(MarketAPI market) {
        return acceptFaction(market.getFaction());
    }

    protected abstract boolean acceptFaction(FactionAPI faction);
}
