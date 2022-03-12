package stelnet.util;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;

public class TableCellHelper {

    public static int getAvailable(MarketAPI buyMarket, String commodityId) {
        CommodityOnMarketAPI buyFromCommodity = buyMarket.getCommodityData(commodityId);
        return TableCellHelper.getAvailable(buyFromCommodity);
    }

    public static int getAvailable(CommodityOnMarketAPI commodity) {
        String commodityId = commodity.getId();
        MarketAPI market = commodity.getMarket();
        if (commodityId == null || market == null) {
            return 0;
        }
        return getAvailableOnMarket(market, commodityId);
    }

    public static Color getFactionColor(FactionAPI faction) {
        if (faction == null) {
            return Misc.getGrayColor();
        }
        return faction.getColor();
    }

    public static Color getClaimingFactionColor(MarketAPI market) {
        FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
        return getFactionColor(faction);
    }

    public static int getDemand(MarketAPI sellMarket, String commodityId) {
        CommodityOnMarketAPI sellToMarketCommodity = sellMarket.getCommodityData(commodityId);
        return TableCellHelper.getDemand(sellMarket, sellToMarketCommodity);
    }

    public static int getDemand(MarketAPI market, CommodityOnMarketAPI commodity) {
        int demandIcons = commodity.getMaxDemand();
        if (!commodity.getCommodity().isPrimary()) {
            CommodityOnMarketAPI primary = market.getCommodityData(commodity.getCommodity().getDemandClass());
            demandIcons = primary.getMaxDemand();
        }
        int demand = (int) (commodity.getCommodity().getEconUnit() * demandIcons);
        demand -= commodity.getPlayerTradeNetQuantity();
        return demand;
    }

    public static String getLocation(MarketAPI market) {
        return market.getName() + " - " + market.getFaction().getDisplayName();
    }

    private static int getAvailableOnMarket(MarketAPI market, String commodityId) {
        float available = 0;
        available += getAvailableOnMarket(market.getSubmarket(Submarkets.SUBMARKET_OPEN), commodityId);
        available += getAvailableOnMarket(market.getSubmarket(Submarkets.GENERIC_MILITARY), commodityId);
        available += getAvailableOnMarket(market.getSubmarket(Submarkets.SUBMARKET_BLACK), commodityId);
        return (int) smartRounding(available);
    }

    private static float getAvailableOnMarket(SubmarketAPI submarket, String commodityId) {
        if (submarket == null) {
            return 0;
        }
        return submarket.getCargo().getCommodityQuantity(commodityId);
    }

    private static float smartRounding(float number) {
        number = 5 * Math.round(number / 5);
        if (number > 100) {
            number = 10 * Math.round(number / 10);
        }
        if (number > 1000) {
            number = 100 * Math.round(number / 100);
        }
        return (int) number;
    }
}
