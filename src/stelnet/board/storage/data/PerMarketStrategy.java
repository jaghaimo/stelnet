package stelnet.board.storage.data;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.board.storage.FilterManager;
import stelnet.util.CargoUtils;
import stelnet.util.CollectionReducer;
import stelnet.util.StorageUtils;

@Log4j
/**
 * Displays storage content grouped by the market it is located at.
 */
public class PerMarketStrategy implements DisplayStrategy {

    @Override
    public List<SubmarketData> getData(FilterManager filterManager) {
        List<SubmarketData> data = new LinkedList<>();
        List<SubmarketAPI> storages = StorageUtils.getAllSortedWithAccess();
        for (SubmarketAPI storage : storages) {
            processSubmarket(new LocationData(storage.getMarket()), storage, filterManager, data);
        }
        return data;
    }

    protected void processSubmarket(
        LocationData locationData,
        SubmarketAPI storage,
        FilterManager filterManager,
        List<SubmarketData> data
    ) {
        CargoAPI storageCargo = storage.getCargo();
        CargoAPI items = getItems(filterManager, storageCargo);
        List<FleetMemberAPI> ships = getShips(filterManager, storageCargo);
        String name = storage.getMarket().getName();
        log.debug("Found " + items.getStacksCopy().size() + " items in " + name);
        log.debug("Found " + ships.size() + " ships in " + name);
        data.add(new SubmarketData(locationData, items, ships));
    }

    private CargoAPI getItems(FilterManager filterManager, CargoAPI storageCargo) {
        CargoAPI items = storageCargo.createCopy();
        List<CargoStackAPI> cargoStacks = storageCargo.getStacksCopy();
        CollectionReducer.reduce(cargoStacks, filterManager.getItemFilters());
        CargoUtils.replaceCargoStacks(items, cargoStacks);
        return items;
    }

    private List<FleetMemberAPI> getShips(FilterManager filterManager, CargoAPI storageCargo) {
        List<FleetMemberAPI> ships = storageCargo.getMothballedShips().getMembersInPriorityOrder();
        CollectionReducer.reduce(ships, filterManager.getShipFilters());
        return ships;
    }
}
