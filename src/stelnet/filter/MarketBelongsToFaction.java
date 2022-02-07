package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class MarketBelongsToFaction extends MarketFilter {

    private final String factionId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return factionId.equalsIgnoreCase(market.getFactionId());
    }
}
