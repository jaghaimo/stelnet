package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import stelnet.util.StelnetHelper;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class MarketCommodityDemand extends MarketFilter {

    private final String commodityId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int demand = StelnetHelper.getDemand(market, commodity);
        return demand > 0;
    }
}
