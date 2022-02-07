package stelnet.filter;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class MarketIsInSystem extends MarketFilter {

    private final String systemId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        StarSystemAPI starSystem = market.getStarSystem();
        if (starSystem == null) {
            return false;
        }
        return systemId.equalsIgnoreCase(starSystem.getId());
    }
}
