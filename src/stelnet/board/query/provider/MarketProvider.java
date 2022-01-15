package stelnet.board.query.provider;

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
import stelnet.util.EconomyUtils;
import stelnet.util.Excluder;
import stelnet.util.SectorUtils;

@Log4j
public class MarketProvider {

    private static transient boolean needsRefresh = true;

    public static List<SectorEntityToken> convertMarketsToTokens(List<MarketAPI> markets) {
        List<SectorEntityToken> tokens = new LinkedList<>();
        for (MarketAPI market : markets) {
            tokens.add(market.getPrimaryEntity());
        }
        return tokens;
    }

    public static List<MarketAPI> getMarkets(boolean refreshContent) {
        List<MarketAPI> markets = EconomyUtils.getMarkets();
        List<Filter> filters = Arrays.<Filter>asList(Excluder.getMarketFilters(), new MarketNotHidden());
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

    public static void updateMarkets(List<MarketAPI> markets) {
        for (MarketAPI market : markets) {
            updateMarket(market);
        }
    }

    public static void updateMarket(MarketAPI market) {
        updateSubmarkets(market);
        updateOfficers(market);
    }

    private static void updateOfficers(MarketAPI market) {
        List<OfficerManagerEvent> managers = SectorUtils.getListenerManager().getListeners(OfficerManagerEvent.class);
        for (OfficerManagerEvent manager : managers) {
            manager.reportPlayerOpenedMarket(market);
        }
    }

    private static void updateSubmarkets(MarketAPI market) {
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            submarket.getPlugin().updateCargoPrePlayerInteraction();
            ListenerUtil.reportSubmarketCargoAndShipsUpdated(submarket);
        }
    }
}
