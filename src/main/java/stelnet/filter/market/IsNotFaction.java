package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class IsNotFaction implements MarketFilter {

    private final String factionId;

    public IsNotFaction(String factionId) {
        this.factionId = factionId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        return !market.getFactionId().equalsIgnoreCase(factionId);
    }
}
