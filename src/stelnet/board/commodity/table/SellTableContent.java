package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;

public class SellTableContent extends MarketTableContent {

    public SellTableContent(final String commodityId, final List<MarketAPI> sellMarket) {
        super(commodityId, sellMarket, new DemandPrice(commodityId));
        createRows();
    }

    @Override
    public Object[] getHeaders(final float width) {
        return getHeader(width, L10n.commodity("HEADER_DEMAND"), L10n.commodity("HEADER_DEFICIT"));
    }

    @Override
    protected MarketTableRow createRowData(final int i, final MarketAPI market) {
        final CommodityOnMarketAPI commodityData = getCommodityData(market);
        final int demand = StelnetHelper.getCommodityDemand(market, commodityData);
        final int deficit = -commodityData.getDeficitQuantity();
        final float sellPrice = price.getUnitPrice(market);
        return new MarketTableRow(i, sellPrice, market, demand, deficit);
    }
}
