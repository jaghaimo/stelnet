package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.util.TableCellHelper;

public class SellTableContent extends MarketTableContent {

    public SellTableContent(String commodityId, List<MarketAPI> sellMarket) {
        super(commodityId, sellMarket, new DemandPrice(commodityId));
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, CommodityL10n.HEADER_DEMAND, CommodityL10n.HEADER_DEFICIT);
    }

    @Override
    protected TableRow createRowData(int i, MarketAPI market) {
        CommodityOnMarketAPI commodityData = getCommodityData(market);
        int demand = TableCellHelper.getDemand(market, commodityData);
        int deficit = -commodityData.getDeficitQuantity();
        float sellPrice = price.getPriceAmount(market);
        return new TableRow(i, sellPrice, market, demand, deficit);
    }
}
