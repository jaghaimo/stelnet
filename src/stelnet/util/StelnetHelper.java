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
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;

/**
 * `Misc` god class equivalent.
 */
@Log4j
public class StelnetHelper {

    /**
     * Singleton, creates or gets an existing instance of a class that implements IntelInfoPlugin.
     * Requires no-args constructor. Used by all "Boards".
     */
    public static <T extends IntelInfoPlugin> T getInstance(Class<T> className) {
        IntelInfoPlugin intel = Global.getSector().getIntelManager().getFirstIntel(className);
        if (intel == null) {
            try {
                @SuppressWarnings("deprecation")
                IntelInfoPlugin board = className.newInstance();
                Global.getSector().getIntelManager().addIntel(board, true);
                intel = board;
            } catch (Exception exception) {
                log.error("Couldn't create board for " + className.getName(), exception);
            }
        }
        return className.cast(intel);
    }

    /**
     * Sum of all cargo stack sizes. Contrary to `CargoAPI.getUsedSpace` it ignores item's size.
     */
    public static int calculateItemQuantity(CargoAPI cargo) {
        float cargoSpace = 0;
        for (CargoStackAPI stack : cargo.getStacksCopy()) {
            cargoSpace += stack.getSize();
        }
        return (int) cargoSpace;
    }

    public static CargoAPI getAllItems(Set<Filter> filters) {
        List<CargoStackAPI> cargoStacks = new LinkedList<>();
        Set<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            cargoStacks.addAll(submarket.getCargo().getStacksCopy());
        }
        CollectionUtils.reduce(cargoStacks, filters);
        return StelnetHelper.makeCargoFromStacks(cargoStacks);
    }

    public static List<FleetMemberAPI> getAllShips(Set<Filter> filters) {
        List<FleetMemberAPI> ships = new LinkedList<>();
        Set<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            ships.addAll(submarket.getCargo().getMothballedShips().getMembersListCopy());
        }
        CollectionUtils.reduce(ships, filters);
        return ships;
    }

    public static Set<SubmarketAPI> getAllWithAccess() {
        Set<SubmarketAPI> availableStorages = Includer.getAbandonedStations();
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            if (Misc.playerHasStorageAccess(market)) {
                SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
                availableStorages.add(storage);
            }
        }
        return availableStorages;
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
        return Global.getSettings().getSpriteName(ModConstants.STELNET_ID, sprite);
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

    public static boolean hasCommodity(String commodityId) {
        return Global.getSettings().getCommoditySpec(commodityId) != null;
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
        Global.getSector().removeTransientScriptsOfClass(className);
        Global.getSector().removeScriptsOfClass(className);
    }

    public static void sendKey(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
            robot.keyRelease(key);
        } catch (AWTException exception) {
            log.warn("Something went wrong sending a key!", exception);
        }
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
}
