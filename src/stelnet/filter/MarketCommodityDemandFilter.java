package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.util.TableCellHelper;

public class MarketCommodityDemandFilter extends MarketFilter {

    private final String commodityId;

    public MarketCommodityDemandFilter(String commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int demand = TableCellHelper.getDemand(market, commodity);
        return demand > 0;
    }
}
