package stelnet.storage.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import lombok.extern.log4j.Log4j;
import stelnet.helper.CargoHelper;
import stelnet.helper.CollectionHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.FilterManager;

@Log4j
public class PerLocationProvider implements DataProvider {

    @Override
    public List<StorageData> getData(FilterManager filterManager) {
        List<StorageData> data = new ArrayList<>();
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            CargoAPI storageCargo = storage.getCargo();
            CargoAPI items = getItems(filterManager, storageCargo);
            List<FleetMemberAPI> ships = getShips(filterManager, storageCargo);
            String name = storage.getMarket().getName();
            log.debug("Found " + items.getStacksCopy().size() + " items in " + name);
            log.debug("Found " + ships.size() + " ships in " + name);
            data.add(new StorageData(new LocationData(storage.getMarket()), items, ships));
        }
        return data;
    }

    private CargoAPI getItems(FilterManager filterManager, CargoAPI storageCargo) {
        CargoAPI items = storageCargo.createCopy();
        List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
        CollectionHelper.reduce(cargoStacks, filterManager.getItemFilters());
        CargoHelper.replaceCargoStacks(items, cargoStacks);
        return items;
    }

    private List<FleetMemberAPI> getShips(FilterManager filterManager, CargoAPI storageCargo) {
        List<FleetMemberAPI> ships = storageCargo.getMothballedShips().getMembersInPriorityOrder();
        CollectionHelper.reduce(ships, filterManager.getShipFilters());
        return ships;
    }
}
