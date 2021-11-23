package stelnet.widget.viewer;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import stelnet.util.StorageUtils;
import stelnet.widget.WidgetL10n;

@Log4j
/**
 * Puts cargo and ships in one "Unified" group.
 */
public class UnifiedStrategy implements DisplayStrategy {

    @Override
    public List<LocationContent> getData(FilteringButtons filteringButtons) {
        Set<Filter> filters = filteringButtons.getFilters();
        CargoAPI items = StorageUtils.getAllItems(filters);
        List<FleetMemberAPI> ships = StorageUtils.getAllShips(filters);
        log.debug("Found " + items.getStacksCopy().size() + " items");
        log.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new LocationContent(getLocationData(), items, ships));
    }

    private LocationInfo getLocationData() {
        return new LocationInfo(
            L10n.get(WidgetL10n.VIEWER_UNIFIED_VIEW_TITLE),
            Misc.getBasePlayerColor(),
            Misc.getDarkPlayerColor()
        );
    }
}
