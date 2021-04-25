package stelnet.commodity.data.content.sell;

import java.util.List;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.commodity.data.content.MarketTableContent;

public class SellTableContent extends MarketTableContent {

    public SellTableContent(String commodityId, List<MarketAPI> markets) {
        super(commodityId, markets, new DemandPrice(commodityId));
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "Demand", "Deficit");
    }

    @Override
    protected Object[] getRow(int i, MarketAPI market) {
        CommodityOnMarketAPI commodity = market.getCommodityData(commodityId);
        float price = getPrice(market);
        int demand = helper.getDemand(market, commodity);
        int deficit = -commodity.getDeficitQuantity();
        return getRow(i, market, commodity, price, demand, deficit);
    }
}
