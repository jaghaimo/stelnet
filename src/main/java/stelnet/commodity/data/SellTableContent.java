package stelnet.commodity.data;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import stelnet.commodity.market.MarketApiWrapper;

import java.util.List;

public class SellTableContent extends MarketTableContent {

    public SellTableContent(String commodityId,  List<MarketApiWrapper> sellMarket) {
        super(commodityId, sellMarket);
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "Demand", "Deficit");
    }

    @Override
    protected RowDataElement createRowData(int i, MarketApiWrapper market) {
        CommodityOnMarketAPI commodity = market.getMarketAPI().getCommodityData(commodityId);
        float price = market.getPriceAmount();
        int demand = TableCellHelper.getDemand(market.getMarketAPI(), commodity);
        int deficit = -commodity.getDeficitQuantity();
        return createRenderableRow(i, market, price, demand, deficit);
    }
}
