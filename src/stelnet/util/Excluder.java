package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONObject;
import stelnet.filter.AnyHasId;
import stelnet.filter.AnyHasTag;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.filter.MarketBelongsToFaction;
import stelnet.filter.MarketIsInSystem;

/**
 * Provides access to user/mod exclusions in the form of a relevant filter objects.
 */
@Log4j
public class Excluder {

    private static final String MARKET_BY_FACTION = "exclude/market_by_faction.csv";
    private static final String MARKET_BY_ID = "exclude/market_by_id.csv";
    private static final String MARKET_BY_SYSTEM = "exclude/market_by_system.csv";
    private static final String MARKET_BY_TAG = "exclude/market_by_tag.csv";

    private static transient List<Filter> filters;

    public static Filter getQuerySubmarketFilter() {
        return Excluder.getSubmarketFilters(
            ConfigConstants.QUERY_USE_OPEN_MARKET,
            ConfigConstants.QUERY_USE_MILITARY_MARKET,
            ConfigConstants.QUERY_USE_BLACK_MARKET,
            ConfigConstants.QUERY_USE_CUSTOM_MARKETS
        );
    }

    public static Filter getViewerSubmarketFilter() {
        return Excluder.getSubmarketFilters(
            ConfigConstants.VIEWER_USE_OPEN_MARKET,
            ConfigConstants.VIEWER_USE_MILITARY_MARKET,
            ConfigConstants.VIEWER_USE_BLACK_MARKET,
            ConfigConstants.VIEWER_USE_CUSTOM_MARKETS
        );
    }

    public static Filter getMarketFilters() {
        if (filters == null) {
            filters = new ArrayList<>();
            filters = getMarketByFactionFilters(filters);
            filters = getMarketByIdFilters(filters);
            filters = getMarketBySystemFilters(filters);
            filters = getMarketByTagFilters(filters);
        }
        return new LogicalNot(new LogicalOr(filters, "Markets"));
    }

    private static Filter getSubmarketFilters(
        boolean useOpenMarket,
        boolean useMilitaryMarket,
        boolean useBlackMarket,
        boolean useCustomMarkets
    ) {
        Filter storage = new AnyHasId(Submarkets.SUBMARKET_STORAGE);
        Filter openMarket = new AnyHasId(Submarkets.SUBMARKET_OPEN);
        Filter militaryMarket = new AnyHasId(Submarkets.GENERIC_MILITARY);
        Filter blackMarket = new AnyHasId(Submarkets.SUBMARKET_BLACK);
        List<Filter> filters = new LinkedList<>();
        if (useOpenMarket) {
            filters.add(openMarket);
        }
        if (useMilitaryMarket) {
            filters.add(militaryMarket);
        }
        if (useBlackMarket) {
            filters.add(blackMarket);
        }
        if (useCustomMarkets) {
            filters.add(
                new LogicalNot(new LogicalOr(Arrays.asList(storage, openMarket, militaryMarket, blackMarket), "Custom"))
            );
        }
        return new LogicalAnd(Arrays.asList(new LogicalNot(storage), new LogicalOr(filters, "Submarkets")));
    }

    private static List<Filter> getMarketByFactionFilters(List<Filter> filters) {
        for (String factionId : getStrings(MARKET_BY_FACTION)) {
            filters.add(new MarketBelongsToFaction(factionId));
        }
        return filters;
    }

    private static List<Filter> getMarketByIdFilters(List<Filter> filters) {
        for (String marketId : getStrings(MARKET_BY_ID)) {
            filters.add(new AnyHasId(marketId));
        }
        return filters;
    }

    private static List<Filter> getMarketBySystemFilters(List<Filter> filters) {
        for (String systemId : getStrings(MARKET_BY_SYSTEM)) {
            filters.add(new MarketIsInSystem(systemId));
        }
        return filters;
    }

    private static List<Filter> getMarketByTagFilters(List<Filter> filters) {
        for (String tag : getStrings(MARKET_BY_TAG)) {
            filters.add(new AnyHasTag(tag));
        }
        return filters;
    }

    private static List<String> getStrings(String path) {
        List<String> strings = new ArrayList<>();
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, ModConstants.STELNET);
            for (int i = 0; i < config.length(); i++) {
                JSONObject row = config.getJSONObject(i);
                strings.add(row.getString("id"));
            }
        } catch (Throwable throwable) {
            log.warn("Skipping invalid file " + path, throwable);
        }
        return strings;
    }
}
