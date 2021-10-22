package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides easy access to Global.getSector().getEconomy() methods.
 */
public class Economy {

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

    public static EconomyAPI getEconomy() {
        return Global.getSector().getEconomy();
    }
}
