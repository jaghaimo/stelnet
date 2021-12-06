package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public class BuyTableContent extends MarketTableContent {

    public BuyTableContent(String commodityId, List<MarketAPI> buyMarket) {
        super(commodityId, buyMarket, new SupplyPrice(commodityId));
        createRows();
    }

    @Override
    public Object[] getHeaders(float width) {
        return getHeader(width, CommodityL10n.HEADER_AVAILABLE, CommodityL10n.HEADER_EXCESS);
    }

    @Override
    protected TableRow createRowData(int i, MarketAPI market) {
        CommodityOnMarketAPI commodityData = getCommodityData(market);
        int available = TableCellHelper.getAvailable(commodityData);
        int excess = commodityData.getExcessQuantity();
        float buyPrice = price.getPriceAmount(market);
        return new TableRow(i, buyPrice, market, available, excess);
    }
}
