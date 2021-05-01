package stelnet.commodity.data;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
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
        CommodityOnMarketAPI commodity = market.getMarketAPI().getCommodityData(commodityId);
        float price = market.getPriceAmount();
        int available = TableCellHelper.getAvailable(commodity);
        int excess = commodity.getExcessQuantity();
        return createRenderableRow(i, market, price, available, excess);
    }
}
