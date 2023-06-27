package stelnet.widget.viewer;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;
import stelnet.util.L10n;

@Log4j
@RequiredArgsConstructor
/**
 * Variation of `PerMarketStrategy` that groups cargo and ships per submarket of one one market.
 */
public class InMarketStrategy extends PerMarketStrategy {

    private final MarketAPI market;

    @Override
    public List<LocationContent> getData(final ButtonManager filteringButtons) {
        if (market == null) {
            log.debug("No market set, returning empty data.");
            return getEmptyData();
        }
        log.debug("Adding data for market " + market.getName());
        final List<LocationContent> data = new LinkedList<>();
        for (final SubmarketAPI submarket : getSubmarkets(market)) {
            log.debug("Processing submarket " + submarket.getNameOneLine());
            processSubmarket(new LocationInfo(submarket), submarket, filteringButtons, data);
        }
        return data;
    }

    private List<SubmarketAPI> getSubmarkets(final MarketAPI market) {
        final List<SubmarketAPI> submarkets = market.getSubmarketsCopy();
        CollectionUtils.reduce(submarkets, Excluder.getSubmarketFilter());
        return submarkets;
    }

    private List<LocationContent> getEmptyData() {
        return Collections.singletonList(
            new LocationContent(
                new LocationInfo(
                    L10n.widget("VIEWER_NO_MARKETS"),
                    Misc.getBasePlayerColor(),
                    Misc.getDarkPlayerColor()
                ),
                Global.getFactory().createCargo(true),
                Collections.<FleetMemberAPI>emptyList()
            )
        );
    }
}
