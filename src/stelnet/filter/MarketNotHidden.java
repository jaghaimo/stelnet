package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotHidden extends MarketFilter {

    @Override
    public boolean accept(MarketAPI market) {
        return !market.isHidden();
    }
}
