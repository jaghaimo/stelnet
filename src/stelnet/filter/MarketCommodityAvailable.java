package stelnet.filter;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.TableCellHelper;

@RequiredArgsConstructor
public class MarketCommodityAvailable extends MarketFilter {

    private final String commodityId;

    @Override
    protected boolean acceptMarket(MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        int available = TableCellHelper.getAvailable(commodity);
        return available > 0;
    }
}
