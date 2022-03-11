package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.Price;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.util.TableCellHelper;

public abstract class ProfitCalculator {

    private static final int MINIMUM_QUANTITY = 1000;

    protected float calculateProfit(MarketAPI buyMarket, MarketAPI sellMarket, String commodityId) {
        Price supplyPrice = new SupplyPrice(commodityId);
        Price demandPrice = new DemandPrice(commodityId);
        float buyPrice = supplyPrice.getUnitPrice(buyMarket);
        float sellPrice = demandPrice.getUnitPrice(sellMarket);
        if (buyPrice >= sellPrice) {
            return 0;
        }

        int available = getAvailable(buyMarket, commodityId);
        int demand = getDemand(sellMarket, commodityId);
        int quantity = Math.min(available, demand);

        if (quantity < MINIMUM_QUANTITY) {
            return 0;
        }

        float bought = supplyPrice.getTotalPrice(buyMarket, quantity);
        float sold = demandPrice.getTotalPrice(sellMarket, quantity);
        return sold - bought;
    }

    protected int getAvailable(MarketAPI buyMarket, String commodityId) {
        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        return TableCellHelper.getAvailable(buyFromCommodity);
    }

    protected int getDemand(MarketAPI sellMarket, String commodityId) {
        CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        return TableCellHelper.getDemand(sellMarket, sellToMarketCommodity);
    }
}
