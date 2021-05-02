package stelnet.commodity.data;

import java.util.List;

import stelnet.commodity.market.MarketApiWrapper;

public class BuyTableContent extends MarketTableContent {

    public BuyTableContent(String commodityId, List<MarketApiWrapper> buyMarket) {
        super(commodityId, buyMarket);
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "Available", "Excess");
    }

    @Override
    protected RowDataElement createRowData(int i, MarketApiWrapper market) {
        int excess = market.getExcessQuantity(commodityId);
        int demand = market.getDemand(commodityId);
        return createRowData(i, market, demand, excess);
    }
}
