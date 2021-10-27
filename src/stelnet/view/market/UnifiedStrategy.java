package stelnet.view.market;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.util.L10n;
import stelnet.util.StorageUtils;

@Log4j
/**
 * Puts cargo and ships in one "Unified" group.
 */
public class UnifiedStrategy implements DisplayStrategy {

    @Override
    public List<LocationContent> getData(FilteringButtons filteringButtons) {
        CargoAPI items = StorageUtils.getAllItems(filteringButtons.getItemFilters());
        List<FleetMemberAPI> ships = StorageUtils.getAllShips(filteringButtons.getShipFilters());
        log.debug("Found " + items.getStacksCopy().size() + " items");
        log.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new LocationContent(getLocationData(), items, ships));
    }

    private LocationInfo getLocationData() {
        return new LocationInfo(L10n.get("viewUnifiedViewTitle"), Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
    }
}
