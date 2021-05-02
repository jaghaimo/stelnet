package stelnet.commodity.data;

import java.util.List;

import stelnet.commodity.market.MarketApiWrapper;
import stelnet.ui.RowDataElement;

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
        int demand = market.getDemand(commodityId);
        int deficit = -market.getDeficitQuantity(commodityId);
        return createRowData(i, market, demand, deficit);
    }
}
