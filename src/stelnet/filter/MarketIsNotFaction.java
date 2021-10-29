package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class MarketIsNotFaction extends MarketFilter {

    private final String factionId;

    public MarketIsNotFaction(String factionId) {
        this.factionId = factionId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getFactionId().equalsIgnoreCase(factionId);
    }
}
