package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Provides easy access to Global.getSector().getEconomy() methods.
 */
public class EconomyUtils {

    public static List<CommoditySpecAPI> getAllCommodities() {
        List<CommoditySpecAPI> commodities = new ArrayList<>();
        for (String commodityId : getAllCommodityIds()) {
            commodities.add(getCommoditySpec(commodityId));
        }
        return commodities;
    }

    public static List<String> getAllCommodityIds() {
        return getEconomy().getAllCommodityIds();
    }

    public static CommoditySpecAPI getCommoditySpec(String commodityId) {
        return getEconomy().getCommoditySpec(commodityId);
    }

    public static MarketAPI getMarket(String id) {
        return getEconomy().getMarket(id);
    }

    public static List<MarketAPI> getMarkets() {
        return getEconomy().getMarketsCopy();
    }

    public static List<SubmarketAPI> getSubmarkets(List<MarketAPI> markets) {
        List<SubmarketAPI> submarkets = new LinkedList<>();
        for (MarketAPI market : markets) {
            submarkets.addAll(market.getSubmarketsCopy());
        }
        return submarkets;
    }

    private static EconomyAPI getEconomy() {
        return Global.getSector().getEconomy();
    }
}
