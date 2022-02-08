package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import stelnet.filter.Filter;

public class StorageUtils {

    public static CargoAPI getAllItems() {
        return getAllItems(Collections.<Filter>emptySet());
    }

    public static CargoAPI getAllItems(Set<Filter> filters) {
        List<CargoStackAPI> cargoStacks = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            cargoStacks.addAll(submarket.getCargo().getStacksCopy());
        }
        CollectionUtils.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    public static int getAllItemCount() {
        CargoAPI cargo = getAllItems();
        return CargoUtils.calculateItemQuantity(cargo);
    }

    public static List<FleetMemberAPI> getAllShips() {
        return getAllShips(Collections.<Filter>emptySet());
    }

    public static List<FleetMemberAPI> getAllShips(Set<Filter> filters) {
        List<FleetMemberAPI> ships = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            ships.addAll(submarket.getCargo().getMothballedShips().getMembersListCopy());
        }
        CollectionUtils.reduce(ships, filters);
        return ships;
    }

    public static int getAllShipCount() {
        return getAllShips().size();
    }

    public static List<SubmarketAPI> getAllWithAccess() {
        List<SubmarketAPI> availableStorages = new ArrayList<>();
        for (MarketAPI market : EconomyUtils.getMarkets()) {
            if (Misc.playerHasStorageAccess(market)) {
                SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
                availableStorages.add(storage);
            }
        }
        addDerelictStations(availableStorages);
        return availableStorages;
    }

    public static List<SubmarketAPI> getAllSortedWithAccess() {
        List<SubmarketAPI> availableStorages = getAllWithAccess();
        Collections.sort(
            availableStorages,
            new Comparator<SubmarketAPI>() {
                @Override
                public int compare(SubmarketAPI s1, SubmarketAPI s2) {
                    float s1distance = DistanceCalculator.getDistanceToPlayerLY(s1.getMarket().getPrimaryEntity());
                    float s2distance = DistanceCalculator.getDistanceToPlayerLY(s2.getMarket().getPrimaryEntity());
                    return Math.round(s1distance - s2distance);
                }
            }
        );
        return availableStorages;
    }

    private static void addDerelictStations(List<SubmarketAPI> submarkets) {
        String[] stations = new String[] {
            "corvus_abandoned_station",
            "yma_abandoned_station",
            "mairaath_abandoned_station1",
        };
        for (String station : stations) {
            SubmarketAPI submarket = getStorage(station);
            if (submarket != null) {
                submarkets.add(submarket);
            }
        }
    }

    private static SubmarketAPI getStorage(String station) {
        SectorEntityToken token = Global.getSector().getEntityById(station);
        if (token == null) {
            return null;
        }
        MarketAPI market = token.getMarket();
        if (market == null) {
            return null;
        }
        return market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
    }
}
