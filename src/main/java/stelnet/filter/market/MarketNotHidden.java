package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketNotHidden implements MarketFilter {

    @Override
    public boolean accept(MarketAPI market) {
        return !market.isHidden();
    }
}
