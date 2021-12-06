package stelnet.board.commodity.market.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.board.commodity.CommodityL10n;
import stelnet.board.commodity.market.price.Price;
import stelnet.board.commodity.market.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public class BuyTableContent extends MarketTableContent {

    private final Price price;

    public BuyTableContent(String commodityId, List<MarketAPI> buyMarket) {
        super(commodityId, buyMarket);
        price = new SupplyPrice(commodityId);
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
        return createRowData(i, market, available, excess);
    }

    @Override
    protected float getPrice(MarketAPI market) {
        return price.getPriceAmount(market);
    }
}
