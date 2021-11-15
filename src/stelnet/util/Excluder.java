package stelnet.util;

import com.fs.starfarer.api.Global;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONObject;
import stelnet.filter.AnyHasTag;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.filter.MarketBelongsToFaction;
import stelnet.filter.MarketHasId;
import stelnet.filter.MarketIsInSystem;

/**
 * Provides access to user/mod exclusions in the form of a relevant filter objects.
 */
@Log4j
public class Excluder {

    private static final String MARKET_BY_FACTION = "data/config/stelnet/market_by_faction.csv";
    private static final String MARKET_BY_ID = "data/config/stelnet/market_by_id.csv";
    private static final String MARKET_BY_SYSTEM = "data/config/stelnet/market_by_system.csv";
    private static final String MARKET_BY_TAG = "data/config/stelnet/market_by_tag.csv";

    private static transient List<Filter> filters;

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

    private static List<Filter> getMarketByFactionFilters(List<Filter> filters) {
        for (String factionId : getStrings(MARKET_BY_FACTION)) {
            filters.add(new MarketBelongsToFaction(factionId));
        }
        return filters;
    }

    private static List<Filter> getMarketByIdFilters(List<Filter> filters) {
        for (String marketId : getStrings(MARKET_BY_ID)) {
            filters.add(new MarketHasId(marketId));
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
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, TagConstants.STELNET);
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
