package stelnet.storage.data;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;

import stelnet.helper.LogHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.FilterManager;

public class UnifiedProvider implements DataProvider {

    private FilterManager filterManager;

    public UnifiedProvider(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public List<StorageData> getData() {
        CargoAPI items = StorageHelper.getAllItems(filterManager.getItemFilters());
        List<FleetMemberAPI> ships = StorageHelper.getAllShips(filterManager.getShipFilters());
        LogHelper.debug("Found " + items.getStacksCopy().size() + " items");
        LogHelper.debug("Found " + ships.size() + " ships");
        return Arrays.asList(new StorageData(null, items, ships));
    }

    @Override
    public DataProvider getNext() {
        return new PerLocationProvider(filterManager);
    }
}
