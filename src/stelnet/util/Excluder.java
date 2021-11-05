package stelnet.util;

import com.fs.starfarer.api.Global;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONObject;
import stelnet.filter.MarketFilter;
import stelnet.filter.MarketIsNotFaction;
import stelnet.filter.MarketIsNotId;
import stelnet.filter.MarketIsNotInSystem;
import stelnet.filter.MarketIsNotTagged;

/**
 * Provides access to user/mod exclusions in the form of a relevant filter
 * objects.
 */
@Log4j
public class Excluder {

    private static final String FACTIONS = "data/config/stelnet/factions.csv";
    private static final String MARKETS = "data/config/stelnet/markets.csv";
    private static final String SYSTEMS = "data/config/stelnet/systems.csv";
    private static final String TAGS = "data/config/stelnet/tags.csv";

    public static List<MarketFilter> getMarketFilters() {
        List<MarketFilter> filters = new ArrayList<>();
        filters = getFactionFilters(filters);
        filters = getMarketIdFilters(filters);
        filters = getSystemFilters(filters);
        filters = getTagFilters(filters);
        return filters;
    }

    private static List<MarketFilter> getFactionFilters(List<MarketFilter> filters) {
        for (String factionId : getStrings(FACTIONS)) {
            filters.add(new MarketIsNotFaction(factionId));
        }
        return filters;
    }

    private static List<MarketFilter> getMarketIdFilters(List<MarketFilter> filters) {
        for (String marketId : getStrings(MARKETS)) {
            filters.add(new MarketIsNotId(marketId));
        }
        return filters;
    }

    private static List<MarketFilter> getSystemFilters(List<MarketFilter> filters) {
        for (String systemId : getStrings(SYSTEMS)) {
            filters.add(new MarketIsNotInSystem(systemId));
        }
        return filters;
    }

    private static List<MarketFilter> getTagFilters(List<MarketFilter> filters) {
        for (String tag : getStrings(TAGS)) {
            filters.add(new MarketIsNotTagged(tag));
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
