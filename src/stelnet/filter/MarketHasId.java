package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MarketHasId extends MarketFilter {

    private final String id;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return id.equalsIgnoreCase(market.getId());
    }
}
