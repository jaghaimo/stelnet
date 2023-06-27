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
import stelnet.util.StelnetHelper;

@Log4j
/**
 * Puts cargo and ships in one "Unified" group.
 */
public class UnifiedStrategy implements DisplayStrategy {

    @Override
    public List<LocationContent> getData(final ButtonManager filteringButtons) {
        final Set<Filter> filters = filteringButtons.getFilters();
        final CargoAPI items = StelnetHelper.getAllItems(filters);
        final List<FleetMemberAPI> ships = StelnetHelper.getAllShips(filters);
        log.debug("Found " + items.getStacksCopy().size() + " items");
        log.debug("Found " + ships.size() + " ships");
        return Collections.singletonList(new LocationContent(getLocationData(), items, ships));
    }

    private LocationInfo getLocationData() {
        return new LocationInfo(
            L10n.widget("VIEWER_UNIFIED_VIEW_TITLE"),
            Misc.getBasePlayerColor(),
            Misc.getDarkPlayerColor()
        );
    }
}
