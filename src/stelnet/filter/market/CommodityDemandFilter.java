package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.util.TableCellHelper;

public class CommodityDemandFilter implements MarketFilter {

    private final String commodityId;

    public CommodityDemandFilter(String commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int demand = TableCellHelper.getDemand(market, commodity);
        return demand > 0;
    }
}
