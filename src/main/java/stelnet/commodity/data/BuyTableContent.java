package stelnet.commodity.data;

import stelnet.commodity.market.MarketApiWrapper;

import java.util.List;

public class BuyTableContent extends MarketTableContent {

    public BuyTableContent(String commodityId, List<MarketApiWrapper> buyMarket) {
        super(commodityId, buyMarket);
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "Available", "Excess");
    }

    // TODO: Ideally over here we would register a callback with a row
    @Override
    protected RowDataElement createRowData(int i, MarketApiWrapper market) {
        int excess = market.getExcessQuantity(commodityId);
        int demand = market.getDemand(commodityId);
        return createRowData(i, market, demand, excess);
    }
}
