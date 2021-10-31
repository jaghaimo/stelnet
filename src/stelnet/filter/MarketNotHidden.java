package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotHidden extends MarketFilter {

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return !market.isHidden();
    }
}
