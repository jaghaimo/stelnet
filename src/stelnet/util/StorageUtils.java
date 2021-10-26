package stelnet.util;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;

public class StorageUtils {

    public static CargoAPI getAllItems() {
        Set<CargoStackFilter> filters = new HashSet<>();
        return getAllItems(filters);
    }

    public static CargoAPI getAllItems(Set<CargoStackFilter> filters) {
        List<CargoStackAPI> cargoStacks = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            cargoStacks.addAll(submarket.getCargo().getStacksCopy());
        }
        CollectionReducer.reduce(cargoStacks, filters);
        return CargoUtils.makeCargoFromStacks(cargoStacks);
    }

    public static int getAllItemCount() {
        CargoAPI cargo = getAllItems();
        return CargoUtils.calculateItemQuantity(cargo);
    }

    public static List<FleetMemberAPI> getAllShips() {
        Set<FleetMemberFilter> filters = new HashSet<>();
        return getAllShips(filters);
    }

    public static List<FleetMemberAPI> getAllShips(Set<FleetMemberFilter> filters) {
        List<FleetMemberAPI> ships = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            ships.addAll(submarket.getCargo().getMothballedShips().getMembersListCopy());
        }
        CollectionReducer.reduce(ships, filters);
        return ships;
    }

    public static int getAllShipCount() {
        return getAllShips().size();
    }

    public static List<SubmarketAPI> getAllWithAccess() {
        List<SubmarketAPI> availableStorages = new ArrayList<>();
        for (MarketAPI market : Economy.getMarkets()) {
            if (Misc.playerHasStorageAccess(market)) {
                SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
                availableStorages.add(storage);
            }
        }
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
}