package stelnet.helper;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignClockAPI;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

public class GlobalHelper {

    public static CargoAPI createCargo(boolean unlimitedStacks) {
        return Global.getFactory().createCargo(unlimitedStacks);
    }

    public static List<CommoditySpecAPI> getAllCommodities() {
        List<CommoditySpecAPI> commodities = new ArrayList<>();
        for (String commodityId : getAllCommodityIds()) {
            commodities.add(getCommoditySpec(commodityId));
        }
        return commodities;
    }

    public static List<String> getAllCommodityIds() {
        return Global.getSector().getEconomy().getAllCommodityIds();
    }

    public static CommoditySpecAPI getCommoditySpec(String commodityId) {
        return Global.getSector().getEconomy().getCommoditySpec(commodityId);
    }

    public static CampaignClockAPI getCurrentClock() {
        return Global.getSector().getClock();
    }

    public static EconomyAPI getEconomy() {
        return Global.getSector().getEconomy();
    }

    public static int getEconomyIterPerMonth() {
        return Global.getSettings().getInt("economyIterPerMonth");
    }

    public static IntelManagerAPI getIntelManager() {
        return Global.getSector().getIntelManager();
    }

    public static ListenerManagerAPI getListenerManager() {
        return Global.getSector().getListenerManager();
    }

    public static List<MarketAPI> getMarkets() {
        return Global.getSector().getEconomy().getMarketsCopy();
    }

    public static FactionAPI getPlayerFaction() {
        return Global.getSector().getPlayerFaction();
    }

    public static CampaignFleetAPI getPlayerFleet() {
        return Global.getSector().getPlayerFleet();
    }

}
