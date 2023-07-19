package stelnet.board.query.provider;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;
import com.fs.starfarer.api.impl.campaign.events.OfficerManagerEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;
import stelnet.filter.MarketNotHidden;
import stelnet.util.CollectionUtils;
import stelnet.util.Excluder;

@Log4j
public class MarketProvider {

    private static transient boolean needsRefresh = true;

    public static List<SectorEntityToken> convertMarketsToTokens(final List<MarketAPI> markets) {
        final List<SectorEntityToken> tokens = new LinkedList<>();
        for (final MarketAPI market : markets) {
            tokens.add(market.getPrimaryEntity());
        }
        return tokens;
    }

    public static List<MarketAPI> getMarkets(final boolean refreshContent) {
        final List<MarketAPI> markets = Global.getSector().getEconomy().getMarketsCopy();
        final List<Filter> filters = Arrays.<Filter>asList(Excluder.getMarketFilter(), new MarketNotHidden());
        CollectionUtils.reduce(markets, filters);
        if (refreshContent && needsRefresh) {
            log.debug("Refreshing all markets, this may take a moment");
            updateMarkets(markets);
            needsRefresh = false;
        }
        return markets;
    }

    public static void reset() {
        needsRefresh = true;
    }

    public static void updateMarkets(final List<MarketAPI> markets) {
        for (final MarketAPI market : markets) {
            updateMarket(market);
        }
    }

    public static void updateMarket(final MarketAPI market) {
        updateOfficers(market);
        updateSubmarkets(market);
        ListenerUtil.reportPlayerOpenedMarketAndCargoUpdated(market);
    }

    private static void updateOfficers(final MarketAPI market) {
        final List<OfficerManagerEvent> managers = Global
            .getSector()
            .getListenerManager()
            .getListeners(OfficerManagerEvent.class);
        for (final OfficerManagerEvent manager : managers) {
            manager.reportPlayerOpenedMarket(market);
        }
    }

    private static void updateSubmarkets(final MarketAPI market) {
        for (final SubmarketAPI submarket : market.getSubmarketsCopy()) {
            submarket.getPlugin().updateCargoPrePlayerInteraction();
            ListenerUtil.reportSubmarketCargoAndShipsUpdated(submarket);
        }
    }
}
