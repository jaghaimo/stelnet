package stelnet.storage.data;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.helper.CargoHelper;
import stelnet.helper.CollectionHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.FilterManager;

public class PerLocationProvider implements DataProvider {

    private FilterManager filterManager;

    public PerLocationProvider(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public List<StorageData> getData() {
        List<StorageData> data = new ArrayList<>();
        List<SubmarketAPI> storages = StorageHelper.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            CargoAPI storageCargo = storage.getCargo();
            CargoAPI items = getItems(storageCargo);
            List<FleetMemberAPI> ships = getShips(storageCargo);
            data.add(new StorageData(storage, items, ships));
        }
        return data;
    }

    @Override
    public DataProvider getNext() {
        return new UnifiedProvider(filterManager);
    }

    private CargoAPI getItems(CargoAPI storageCargo) {
        CargoAPI items = storageCargo.createCopy();
        List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
        CollectionHelper.reduce(cargoStacks, filterManager.getCargoFilters());
        CargoHelper.replaceCargoStacks(items, cargoStacks);
        return items;
    }

    private List<FleetMemberAPI> getShips(CargoAPI storageCargo) {
        List<FleetMemberAPI> ships = storageCargo.getMothballedShips().getMembersInPriorityOrder();
        CollectionHelper.reduce(ships, filterManager.getFleetMemberFilters());
        return ships;
    }
}
