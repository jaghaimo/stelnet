package stelnet.util;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;
import stelnet.filter.MarketNotHidden;

@Log4j
public class MarketUtils {

    private static long lastUpdate = Long.MIN_VALUE;

    public static List<SectorEntityToken> convertMarketsToTokens(List<MarketAPI> markets) {
        List<SectorEntityToken> tokens = new LinkedList<>();
        for (MarketAPI market : markets) {
            tokens.add(market.getPrimaryEntity());
        }
        return tokens;
    }

    public static Set<MarketAPI> extractMarketsFromSubmarkets(List<SubmarketAPI> submarkets) {
        Set<MarketAPI> markets = new HashSet<>();
        for (SubmarketAPI submarket : submarkets) {
            markets.add(submarket.getMarket());
        }
        return markets;
    }

    public static List<MarketAPI> getMarkets() {
        return getMarkets(true);
    }

    public static List<MarketAPI> getMarkets(boolean refreshContent) {
        List<MarketAPI> markets = EconomyUtils.getMarkets();
        List<Filter> filters = Arrays.<Filter>asList(Excluder.getMarketFilters(), new MarketNotHidden());
        CollectionUtils.reduce(markets, filters);
        if (refreshContent && needsRefresh()) {
            log.info("Refreshing all markets, this may take a while");
            updateMarketPrePlayerInteraction(markets);
            lastUpdate = SectorUtils.now();
        }
        return markets;
    }

    public static List<SubmarketAPI> getSubmarkets() {
        return getSubmarkets(getMarkets());
    }

    public static List<SubmarketAPI> getSubmarkets(List<MarketAPI> markets) {
        List<SubmarketAPI> submarkets = new ArrayList<>();
        for (MarketAPI market : markets) {
            List<SubmarketAPI> marketSubmarkets = market.getSubmarketsCopy();
            submarkets.addAll(marketSubmarkets);
        }
        return submarkets;
    }

    private static boolean needsRefresh() {
        return lastUpdate < SectorUtils.now();
    }

    private static void updateMarketPrePlayerInteraction(List<MarketAPI> markets) {
        for (MarketAPI market : markets) {
            ListenerUtil.reportPlayerOpenedMarket(market);
            updateSubmarketsPrePlayerInteraction(market);
        }
    }

    private static void updateSubmarketsPrePlayerInteraction(MarketAPI market) {
        for (SubmarketAPI submarket : market.getSubmarketsCopy()) {
            submarket.getPlugin().updateCargoPrePlayerInteraction();
        }
    }
}
