package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import stelnet.CommonL10n;
import stelnet.filter.Filter;

/**
 * `Misc` god class equivalent.
 */
@Log4j
public class StelnetHelper {

    public static int calculateItemQuantity(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getSize();
        }
        return (int) cargoSpace;
    }

    public static int calculateShipQuantity(List<FleetMemberAPI> fleet) {
        return fleet.size();
    }

    public static CargoAPI getAllItems(Set<Filter> filters) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            cargoStacks.addAll(submarket.getCargo().getStacksCopy());
        }
        CollectionUtils.reduce(cargoStacks, filters);
        return StelnetHelper.makeCargoFromStacks(cargoStacks);
    }

    public static List<FleetMemberAPI> getAllShips(Set<Filter> filters) {
        List<FleetMemberAPI> ships = new LinkedList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            ships.addAll(submarket.getCargo().getMothballedShips().getMembersListCopy());
        }
        CollectionUtils.reduce(ships, filters);
        return ships;
    }

    public static List<SubmarketAPI> getAllWithAccess() {
        List<SubmarketAPI> availableStorages = Includer.getAbandonedStations();
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            if (Misc.playerHasStorageAccess(market)) {
                SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
                availableStorages.add(storage);
            }
        }
        return availableStorages;
    }

    public static Color getClaimingFactionColor(MarketAPI market) {
        FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
        return getFactionColor(faction);
    }

    public static int getCommodityAvailable(CommodityOnMarketAPI commodity) {
        String commodityId = commodity.getId();
        MarketAPI market = commodity.getMarket();
        if (commodityId == null || market == null) {
            return 0;
        }
        return getCommodityAvailable(market, commodityId);
    }

    public static int getCommodityAvailable(MarketAPI market, String commodityId) {
        float available = 0;
        available += getCommodityAvailable(market.getSubmarket(Submarkets.SUBMARKET_OPEN), commodityId);
        available += getCommodityAvailable(market.getSubmarket(Submarkets.GENERIC_MILITARY), commodityId);
        available += getCommodityAvailable(market.getSubmarket(Submarkets.SUBMARKET_BLACK), commodityId);
        return (int) smartRounding(available);
    }

    public static float getCommodityAvailable(SubmarketAPI submarket, String commodityId) {
        if (submarket == null) {
            return 0;
        }
        return submarket.getCargo().getCommodityQuantity(commodityId);
    }

    public static int getCommodityDemand(MarketAPI market, CommodityOnMarketAPI commodity) {
        int demandIcons = commodity.getMaxDemand();
        if (!commodity.getCommodity().isPrimary()) {
            CommodityOnMarketAPI primary = market.getCommodityData(commodity.getCommodity().getDemandClass());
            demandIcons = primary.getMaxDemand();
        }
        int demand = (int) (commodity.getCommodity().getEconUnit() * demandIcons);
        demand -= commodity.getPlayerTradeNetQuantity();
        return demand;
    }

    public static Color getFactionColor(FactionAPI faction) {
        if (faction == null) {
            return Misc.getGrayColor();
        }
        return faction.getColor();
    }

    public static String getMarketWithFactionName(MarketAPI market) {
        return L10n.get(CommonL10n.MARKET_FACTION, market.getName(), market.getFaction().getDisplayName());
    }

    public static String getSpriteName(String sprite) {
        return Global.getSettings().getSpriteName(ModConstants.STELNET, sprite);
    }

    public static String getStarSystemName(StarSystemAPI starSystem, boolean shortName) {
        if (starSystem == null) {
            return L10n.get(CommonL10n.HYPERSPACE);
        }
        if (shortName) {
            return starSystem.getNameWithLowercaseTypeShort();
        }
        return starSystem.getNameWithLowercaseType();
    }

    public static List<SubmarketAPI> getSubmarkets(List<MarketAPI> markets) {
        List<SubmarketAPI> submarkets = new LinkedList<>();
        for (MarketAPI market : markets) {
            submarkets.addAll(market.getSubmarketsCopy());
        }
        return submarkets;
    }

    /**
     * Used for configuration.
     */
    public static JSONObject loadJson(String filename) {
        try {
            return Global.getSettings().loadJSON(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    /**
     * Used for translations.
     */
    public static JSONObject loadMergedJson(String filename) {
        try {
            return Global.getSettings().getMergedJSONForMod(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    public static CargoAPI makeCargoFromStacks(List<CargoStackAPI> cargoStacks) {
        CargoAPI cargo = Global.getFactory().createCargo(true);
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
        return cargo;
    }

    public static void removeIntel(Class<?> className) {
        IntelManagerAPI manager = Global.getSector().getIntelManager();
        IntelInfoPlugin plugin = manager.getFirstIntel(className);
        while (plugin != null) {
            manager.removeIntel(plugin);
            plugin = manager.getFirstIntel(className);
        }
    }

    public static void removeTransientScripts(Class<?> className) {
        Global.getSector().removeTransientScriptsOfClass(className);
    }

    public static void replaceCargoStacks(CargoAPI cargo, List<CargoStackAPI> cargoStacks) {
        cargo.clear();
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
    }

    public static float smartRounding(float number) {
        number = 5 * Math.round(number / 5);
        if (number > 100) {
            number = 10 * Math.round(number / 10);
        }
        if (number > 1000) {
            number = 100 * Math.round(number / 100);
        }
        return (int) number;
    }

    private static JSONObject getEmptyJsonObject(Exception exception, String filename) {
        log.warn("Failed to read " + filename, exception);
        return new JSONObject();
    }
}
