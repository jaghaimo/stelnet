package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;

public class BuyTableContent extends MarketTableContent {

    public BuyTableContent(final String commodityId, final List<MarketAPI> buyMarket) {
        super(commodityId, buyMarket, new SupplyPrice(commodityId));
        createRows();
    }

    @Override
    public Object[] getHeaders(final float width) {
        return getHeader(width, L10n.commodity("HEADER_AVAILABLE"), L10n.commodity("HEADER_EXCESS"));
    }

    @Override
    protected MarketTableRow createRowData(final int i, final MarketAPI market) {
        final CommodityOnMarketAPI commodityData = getCommodityData(market);
        final int available = StelnetHelper.getCommodityAvailable(commodityData);
        final int excess = commodityData.getExcessQuantity();
        final float buyPrice = price.getUnitPrice(market);
        return new MarketTableRow(i, buyPrice, market, available, excess);
    }
}
