package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public class ProfitCalculator {

    private static final int MINIMUM_AVAILABLE_COMMODITY = 1000;

    protected float calculateProfit(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        Price supplyPrice = new SupplyPrice(commodityId);
        Price demandPrice = new DemandPrice(commodityId);
        float buyPrice = supplyPrice.getUnitPrice(buyMarket);
        float sellPrice = demandPrice.getUnitPrice(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        CommodityOnMarketAPI sellToCommodity = sellMarket.getCommodityData(commodityId);

        int available = TableCellHelper.getAvailable(buyFromCommodity);
        int demand = TableCellHelper.getDemand(sellMarket, sellToCommodity);
        int quantity = Math.min(available, demand);

        if (quantity < MINIMUM_AVAILABLE_COMMODITY) {
            return 0;
        }

        float bought = supplyPrice.getTotalPrice(buyMarket, quantity);
        float sold = demandPrice.getTotalPrice(sellMarket, quantity);
        return sold - bought;
    }
}
