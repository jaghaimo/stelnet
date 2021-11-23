package stelnet.board.commodity.market.table;

import java.util.List;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.market.MarketApiWrapper;

public class BuyTableContent extends MarketTableContent {

    public BuyTableContent(String commodityId, List<MarketApiWrapper> buyMarket) {
        super(commodityId, buyMarket);
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, CommodityL10n.HEADER_AVAILABLE, CommodityL10n.HEADER_EXCESS);
    }

    @Override
    protected TableRow createRowData(int i, MarketApiWrapper market) {
        int available = market.getAvailable(commodityId);
        int excess = market.getExcessQuantity(commodityId);
        return createRowData(i, market, available, excess);
    }
}
