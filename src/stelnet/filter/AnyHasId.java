package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class AnyHasId extends MarketFilter {

    private final String id;

    @Override
    public boolean accept(Object object) {
        if (object instanceof SubmarketAPI) {
            return acceptSubmarket((SubmarketAPI) object);
        }
        return super.accept(object);
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        return id.equalsIgnoreCase(market.getId());
    }

    protected boolean acceptSubmarket(SubmarketAPI submarket) {
        return id.equalsIgnoreCase(submarket.getSpecId());
    }
}
