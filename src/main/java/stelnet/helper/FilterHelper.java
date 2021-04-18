package stelnet.helper;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.Global;

import org.json.JSONArray;
import org.json.JSONObject;

import stelnet.filter.market.IsNotFaction;
import stelnet.filter.market.IsNotId;
import stelnet.filter.market.IsNotInSystem;
import stelnet.filter.market.IsNotTagged;
import stelnet.filter.market.MarketFilter;

public class FilterHelper {

    private static String FACTIONS = "data/config/stelnet/factions.csv";
    private static String MARKETS = "data/config/stelnet/markets.csv";
    private static String SYSTEMS = "data/config/stelnet/systems.csv";
    private static String TAGS = "data/config/stelnet/tags.csv";

    public static List<MarketFilter> getBlacklistMarketFilters() {
        List<MarketFilter> filters = new ArrayList<>();
        filters = getFactionFilters(filters);
        filters = getMarketFilters(filters);
        filters = getSystemFilters(filters);
        filters = getTagFilters(filters);
        return filters;
    }

    private static List<MarketFilter> getFactionFilters(List<MarketFilter> filters) {
        for (String factionId : getStrings(FACTIONS)) {
            filters.add(new IsNotFaction(factionId));
        }
        return filters;
    }

    private static List<MarketFilter> getMarketFilters(List<MarketFilter> filters) {
        for (String marketId : getStrings(MARKETS)) {
            filters.add(new IsNotId(marketId));
        }
        return filters;
    }

    private static List<MarketFilter> getSystemFilters(List<MarketFilter> filters) {
        for (String systemId : getStrings(SYSTEMS)) {
            filters.add(new IsNotInSystem(systemId));
        }
        return filters;
    }

    private static List<MarketFilter> getTagFilters(List<MarketFilter> filters) {
        for (String tag : getStrings(TAGS)) {
            filters.add(new IsNotTagged(tag));
        }
        return filters;
    }

    private static List<String> getStrings(String path) {
        List<String> strings = new ArrayList<>();
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, "stelnet");
            for (int i = 0; i < config.length(); i++) {
                JSONObject row = config.getJSONObject(i);
                strings.add(row.getString("id"));
            }
        } catch (Throwable throwable) {
        }
        return strings;
    }
}
