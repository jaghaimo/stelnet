package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.board.commodity.data.TableCellHelper;

public class CommodityAvailableFilter implements MarketFilter {

    private final String commodityId;

    public CommodityAvailableFilter(String commodityId) {
        this.commodityId = commodityId;
    }

    @Override
    public boolean accept(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int available = TableCellHelper.getAvailable(commodity);
        if (available <= 0) {
            return false;
        }
        return true;
    }
}
