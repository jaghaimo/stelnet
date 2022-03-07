package stelnet.board.commodity.table.profit;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public class ProfitCalculator {

    private static final int MINIMUM_AVAILABLE_COMMODITY = 400;

    static float calculateProfit(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        Price price = new SupplyPrice(commodityId);
        float buyPrice = price.getPriceAmount(buyMarket);
        float sellPrice = price.getPriceAmount(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        CommodityOnMarketAPI sellToCommodity = sellMarket.getCommodityData(commodityId);

        int available = TableCellHelper.getAvailable(buyFromCommodity);
        int demand = TableCellHelper.getDemand(sellMarket, sellToCommodity);

        if (available < MINIMUM_AVAILABLE_COMMODITY) {
            return 0;
        }

        if (demand > available) {
            demand = available;
        }

        float bought = buyPrice * demand;
        float sold = sellPrice * demand;
        return sold - bought;
    }
}
