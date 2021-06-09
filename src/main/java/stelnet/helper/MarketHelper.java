package stelnet.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;

import stelnet.filter.market.IsNotHidden;
import stelnet.filter.market.MarketFilter;

public class MarketHelper {

    public static Set<MarketAPI> extractMarkets(List<SubmarketAPI> submarkets) {
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
        List<MarketAPI> markets = GlobalSectorHelper.getMarkets();
        List<MarketFilter> filters = FilterHelper.getBlacklistMarketFilters();
        filters.add(new IsNotHidden());
        CollectionHelper.reduce(markets, filters);
        if (refreshContent) {
            updateMarketPrePlayerInteraction(markets);
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

    private static void updateMarketPrePlayerInteraction(List<MarketAPI> markets) {
        for (MarketAPI market : markets) {
            ListenerUtil.reportPlayerOpenedMarket(market);
        }
    }
}
