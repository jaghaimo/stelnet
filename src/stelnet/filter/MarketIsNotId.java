package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNotId extends MarketFilter {

    private final String marketId;

    public MarketIsNotId(String marketId) {
        this.marketId = marketId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getId().equalsIgnoreCase(marketId);
    }
}
