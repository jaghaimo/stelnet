package stelnet.widget.viewer;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.util.Misc;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.util.L10n;
import stelnet.widget.WidgetL10n;

@Log4j
@RequiredArgsConstructor
/**
 * Variation of `PerMarketStrategy` that groups cargo and ships per submarket of one one market.
 */
public class InMarketStrategy extends PerMarketStrategy {

    private final MarketAPI market;

    @Override
    public List<LocationContent> getData(FilteringButtons filteringButtons) {
        if (market == null) {
            log.debug("No market set, returning empty data.");
            return getEmptyData();
        }
        log.debug("Adding data for market " + market.getName());
        List<LocationContent> data = new LinkedList<>();
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            if (Submarkets.SUBMARKET_STORAGE.equals(submarket.getSpecId())) {
                log.debug("Skipping storage");
                continue;
            }
            log.debug("Processing submarket " + submarket.getNameOneLine());
            processSubmarket(new LocationInfo(submarket), submarket, filteringButtons, data);
        }
        return data;
    }

    private List<LocationContent> getEmptyData() {
        return Collections.singletonList(
            new LocationContent(
                new LocationInfo(L10n.get(WidgetL10n.VIEWER_NO_MARKETS), Misc.getTextColor(), Misc.getGrayColor()),
                Global.getFactory().createCargo(true),
                Collections.<FleetMemberAPI>emptyList()
            )
        );
    }
}
