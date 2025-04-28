package stelnet.util;

import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import stelnet.filter.AnyHasId;
import stelnet.filter.AnyHasTag;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.filter.MarketBelongsToFaction;
import stelnet.filter.MarketIsInSystem;
import stelnet.filter.ResultHasId;
import stelnet.settings.BooleanSettings;

/**
 * Provides access to user/mod exclusions in the form of a relevant filter objects.
 */
public class Excluder extends Reader {

    private static final String MARKET_BY_FACTION = "data/stelnet/exclude/market_by_faction.csv";
    private static final String MARKET_BY_ID = "data/stelnet/exclude/market_by_id.csv";
    private static final String MARKET_BY_SYSTEM = "data/stelnet/exclude/market_by_system.csv";
    private static final String MARKET_BY_TAG = "data/stelnet/exclude/market_by_tag.csv";
    private static final String SKILL_BY_ID = "data/stelnet/exclude/skill_by_id.csv";

    private static Filter marketFilter;
    private static Filter skillFilter;
    private static Filter submarketFilter;
    public static Set<Filter> submarketFilters;

    public static void resetCache() {
        marketFilter = null;
        skillFilter = null;
        submarketFilter = null;
    }

    public static Filter getSubmarketFilter() {
        if (submarketFilter == null) {
            submarketFilter = getSubmarketFilters();
        }
        // Turn off caching for now (https://github.com/jaghaimo/stelnet/issues/120).
        return getSubmarketFilters();
    }

    public static Filter getMarketFilter() {
        if (marketFilter == null) {
            List<Filter> marketFilters = new LinkedList<>();
            addMarketByFactionFilters(marketFilters);
            addAnyByIdFilters(marketFilters, MARKET_BY_ID);
            addMarketBySystemFilters(marketFilters);
            addMarketByTagFilters(marketFilters);
            marketFilter = new LogicalNot(new LogicalOr(marketFilters, "Markets"));
        }
        return marketFilter;
    }

    public static Filter getSkillFilter() {
        if (skillFilter == null) {
            List<Filter> skillFilters = new LinkedList<>();
            addAnyByIdFilters(skillFilters, SKILL_BY_ID);
            skillFilter = new LogicalNot(new LogicalOr(skillFilters, "Skills"));
        }
        return skillFilter;
    }

    private static Filter getSubmarketFilters() {
        Filter storage = new AnyHasId(Submarkets.SUBMARKET_STORAGE);
        Filter openMarket = new AnyHasId(Submarkets.SUBMARKET_OPEN);
        Filter militaryMarket = new AnyHasId(Submarkets.GENERIC_MILITARY);
        Filter blackMarket = new AnyHasId(Submarkets.SUBMARKET_BLACK);
        List<Filter> filters = new LinkedList<>();
        if (BooleanSettings.MARKET_USE_OPEN_MARKET.get()) {
            filters.add(openMarket);
        }
        if (BooleanSettings.MARKET_USE_MILITARY_MARKET.get()) {
            filters.add(militaryMarket);
        }
        if (BooleanSettings.MARKET_USE_BLACK_MARKET.get()) {
            filters.add(blackMarket);
        }
        if (BooleanSettings.MARKET_USE_CUSTOM_MARKET.get()) {
            filters.add(
                new LogicalNot(new LogicalOr(Arrays.asList(storage, openMarket, militaryMarket, blackMarket), "Custom"))
            );
        }
        if (submarketFilters != null) {
            filters.removeIf(filter -> !submarketFilters.contains(new ResultHasId(filter.toString())));
        }
        return new LogicalAnd(Arrays.asList(new LogicalNot(storage), new LogicalOr(filters, "Submarkets")));
    }

    private static void addAnyByIdFilters(List<Filter> filters, String filename) {
        for (String marketId : getStrings(filename, ModConstants.STELNET_ID)) {
            filters.add(new AnyHasId(marketId));
        }
    }

    private static void addMarketByFactionFilters(List<Filter> filters) {
        for (String factionId : getStrings(MARKET_BY_FACTION, ModConstants.STELNET_ID)) {
            filters.add(new MarketBelongsToFaction(factionId));
        }
    }

    private static void addMarketBySystemFilters(List<Filter> filters) {
        for (String systemId : getStrings(MARKET_BY_SYSTEM, ModConstants.STELNET_ID)) {
            filters.add(new MarketIsInSystem(systemId));
        }
    }

    private static void addMarketByTagFilters(List<Filter> filters) {
        for (String tag : getStrings(MARKET_BY_TAG, ModConstants.STELNET_ID)) {
            filters.add(new AnyHasTag(tag));
        }
    }
}
