package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.util.TableCellHelper;

public class MarketCommodityAvailableFilter extends MarketFilter {

    private final String commodityId;

    public MarketCommodityAvailableFilter(String commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int available = TableCellHelper.getAvailable(commodity);
        if (available <= 0) {
            return false;
        }
        return true;
    }
}
