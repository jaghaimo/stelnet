package stelnet.storage.data;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.extern.log4j.Log4j;
import stelnet.L10n;
import stelnet.helper.StorageHelper;
import stelnet.storage.FilterManager;

@Log4j
public class UnifiedProvider implements DataProvider {

    private final FilterManager filterManager;

    public UnifiedProvider(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    @Override
    public List<StorageData> getData() {
        CargoAPI items = StorageHelper.getAllItems(filterManager.getItemFilters());
        List<FleetMemberAPI> ships = StorageHelper.getAllShips(filterManager.getShipFilters());
        log.debug("Found " + items.getStacksCopy().size() + " items");
        log.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new StorageData(getLocationData(), items, ships));
    }

    private LocationData getLocationData() {
        return new LocationData(L10n.get("storageUnifiedView"), Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
    }
}
