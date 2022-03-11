package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.List;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public class ProfitCalculator {

    private static final int MINIMUM_AVAILABLE_COMMODITY = 100;
    private static final int MINIMUM_PROFIT_VALUE = 10000;

    static float calculateProfit(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
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

    public static List<MarketAPI> getProfitableSellMarkets(
        MarketAPI buyMarket,
        List<MarketAPI> sellMarkets,
        String commodityId
    ) {
        List<MarketAPI> rows = new ArrayList<>();

        for (MarketAPI sellMarket : sellMarkets) {
            if (ProfitCalculator.calculateProfit(buyMarket, sellMarket, commodityId) <= MINIMUM_PROFIT_VALUE) {
                continue;
            }

            rows.add(sellMarket);
        }

        return rows;
    }
}
