package stelnet.util;

import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.*;
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

    public static void resetCache() {
        marketFilter = null;
        skillFilter = null;
        submarketFilter = null;
    }

    public static Filter getSubmarketFilter() {
        if (submarketFilter == null) {
            submarketFilter = getSubmarketFilters();
        }
        return submarketFilter;
    }

    public static Filter getMarketFilter() {
        if (marketFilter == null) {
            final List<Filter> marketFilters = new LinkedList<>();
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
            final List<Filter> skillFilters = new LinkedList<>();
            addAnyByIdFilters(skillFilters, SKILL_BY_ID);
            skillFilter = new LogicalNot(new LogicalOr(skillFilters, "Skills"));
        }
        return skillFilter;
    }

    private static Filter getSubmarketFilters() {
        final Filter storage = new AnyHasId(Submarkets.SUBMARKET_STORAGE);
        final Filter openMarket = new AnyHasId(Submarkets.SUBMARKET_OPEN);
        final Filter militaryMarket = new AnyHasId(Submarkets.GENERIC_MILITARY);
        final Filter blackMarket = new AnyHasId(Submarkets.SUBMARKET_BLACK);
        final List<Filter> filters = new LinkedList<>();
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
        return new LogicalAnd(Arrays.asList(new LogicalNot(storage), new LogicalOr(filters, "Submarkets")));
    }

    private static void addAnyByIdFilters(final List<Filter> filters, final String filename) {
        for (final String marketId : getStrings(filename, ModConstants.STELNET_ID)) {
            filters.add(new AnyHasId(marketId));
        }
    }

    private static void addMarketByFactionFilters(final List<Filter> filters) {
        for (final String factionId : getStrings(MARKET_BY_FACTION, ModConstants.STELNET_ID)) {
            filters.add(new MarketBelongsToFaction(factionId));
        }
    }

    private static void addMarketBySystemFilters(final List<Filter> filters) {
        for (final String systemId : getStrings(MARKET_BY_SYSTEM, ModConstants.STELNET_ID)) {
            filters.add(new MarketIsInSystem(systemId));
        }
    }

    private static void addMarketByTagFilters(final List<Filter> filters) {
        for (final String tag : getStrings(MARKET_BY_TAG, ModConstants.STELNET_ID)) {
            filters.add(new AnyHasTag(tag));
        }
    }
}
