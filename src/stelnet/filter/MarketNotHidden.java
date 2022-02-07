package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class MarketNotHidden extends MarketFilter {

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return !market.isHidden();
    }
}
