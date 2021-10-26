package stelnet.board.commodity.data;

import java.util.List;
import stelnet.board.commodity.market.MarketApiWrapper;

public class SellTableContent extends MarketTableContent {

    public SellTableContent(String commodityId, List<MarketApiWrapper> sellMarket) {
        super(commodityId, sellMarket);
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, "commodityHeaderDemand", "commodityHeaderDeficit");
    }

    @Override
    protected TableRow createRowData(int i, MarketApiWrapper market) {
        int demand = market.getDemand(commodityId);
        int deficit = -market.getDeficitQuantity(commodityId);
        return createRowData(i, market, demand, deficit);
    }
}
