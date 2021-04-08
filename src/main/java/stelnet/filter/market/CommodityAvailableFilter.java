package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.commodity.extractor.TableCellHelper;

public class CommodityAvailableFilter implements MarketFilter {

    private String commodityId;
    private TableCellHelper helper;

    public CommodityAvailableFilter(String commodityId) {
        this.commodityId = commodityId;
        this.helper = new TableCellHelper();
    }

    @Override
    public boolean accept(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int available = helper.getAvailable(commodity);
        if (available <= 0) {
            return false;
        }
        return true;
    }
}
