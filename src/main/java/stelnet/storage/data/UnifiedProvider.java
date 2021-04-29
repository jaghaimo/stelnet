package stelnet.storage.data;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.helper.LogHelper;
import stelnet.helper.StorageHelper;
import stelnet.storage.FilterManager;

public class UnifiedProvider implements DataProvider {

    private final FilterManager filterManager;

    public UnifiedProvider(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public List<StorageData> getData() {
        CargoAPI items = StorageHelper.getAllItems(filterManager.getItemFilters());
        List<FleetMemberAPI> ships = StorageHelper.getAllShips(filterManager.getShipFilters());
        LogHelper.debug("Found " + items.getStacksCopy().size() + " items");
        LogHelper.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new StorageData(getLocationData(), items, ships));
    }

    @Override
    public DataProvider getNext() {
        return new PerLocationProvider(filterManager);
    }

    private LocationData getLocationData() {
        return new LocationData("Unified View", Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
    }
}
