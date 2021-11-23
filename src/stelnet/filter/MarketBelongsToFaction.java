package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketBelongsToFaction extends MarketFilter {

    private final String factionId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return factionId.equalsIgnoreCase(market.getFactionId());
    }
}
