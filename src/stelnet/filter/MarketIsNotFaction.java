package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNotFaction extends MarketFilter {

    private final String factionId;

    public MarketIsNotFaction(String factionId) {
        this.factionId = factionId;
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return !market.getFactionId().equalsIgnoreCase(factionId);
    }
}
