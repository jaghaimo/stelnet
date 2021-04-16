package stelnet.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;

public class StorageHelper {

    public static CargoAPI getAllItems() {
        Set<CargoStackFilter> filters = new TreeSet<>();
        return getAllItems(filters);
    }

    public static CargoAPI getAllItems(Set<CargoStackFilter> filters) {
        List<CargoStackAPI> cargoStacks = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            cargoStacks.addAll(submarket.getCargo().getStacksCopy());
        }
        CollectionHelper.reduce(cargoStacks, filters);
        return CargoHelper.makeCargoFromStacks(cargoStacks);
    }

    public static int getAllItemCount() {
        CargoAPI cargo = getAllItems();
        return CargoHelper.calculateCargoQuantity(cargo);
    }

    public static List<FleetMemberAPI> getAllShips() {
        Set<FleetMemberFilter> filters = new TreeSet<>();
        return getAllShips(filters);
    }

    public static List<FleetMemberAPI> getAllShips(Set<FleetMemberFilter> filters) {
        List<FleetMemberAPI> ships = new ArrayList<>();
        List<SubmarketAPI> submarkets = getAllWithAccess();
        for (SubmarketAPI submarket : submarkets) {
            ships.addAll(submarket.getCargo().getMothballedShips().getMembersListCopy());
        }
        CollectionHelper.reduce(ships, filters);
        return ships;
    }

    public static int getAllShipCount() {
        return getAllShips().size();
    }

    public static List<SubmarketAPI> getAllWithAccess() {
        List<SubmarketAPI> availableStorages = new ArrayList<>();
        for (MarketAPI market : GlobalHelper.getMarkets()) {
            if (Misc.playerHasStorageAccess(market)) {
                SubmarketAPI storage = market.getSubmarket(Submarkets.SUBMARKET_STORAGE);
                availableStorages.add(storage);
            }
        }
        return availableStorages;

    }

    public static List<SubmarketAPI> getAllSortedWithAccess() {
        List<SubmarketAPI> availableStorages = getAllWithAccess();
        Collections.sort(availableStorages, new Comparator<SubmarketAPI>() {

            @Override
            public int compare(SubmarketAPI s1, SubmarketAPI s2) {
                float s1distance = DistanceHelper.getDistanceToPlayerLY(s1.getMarket().getPrimaryEntity());
                float s2distance = DistanceHelper.getDistanceToPlayerLY(s2.getMarket().getPrimaryEntity());
                return Math.round(s1distance - s2distance);
            }
        });
        return availableStorages;
    }
}
