package stelnet.storage.data;

import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.extern.log4j.Log4j;
import stelnet.storage.FilterManager;
import stelnet.util.L10n;
import stelnet.util.StorageUtils;

@Log4j
public class UnifiedProvider implements DataProvider {

    @Override
    public List<SubmarketData> getData(FilterManager filterManager) {
        CargoAPI items = StorageUtils.getAllItems(filterManager.getItemFilters());
        List<FleetMemberAPI> ships = StorageUtils.getAllShips(filterManager.getShipFilters());
        log.debug("Found " + items.getStacksCopy().size() + " items");
        log.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new SubmarketData(getLocationData(), items, ships));
    }

    private LocationData getLocationData() {
        return new LocationData(L10n.get("storageUnifiedView"), Misc.getBasePlayerColor(),
                Misc.getDarkPlayerColor());
    }
}
