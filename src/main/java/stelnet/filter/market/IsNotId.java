package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class IsNotId implements MarketFilter {

    private String marketId;

    public IsNotId(String marketId) {
        this.marketId = marketId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getId().equalsIgnoreCase(marketId);
    }
}
