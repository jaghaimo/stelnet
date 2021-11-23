package stelnet.config;

import stelnet.filter.Filter;
import stelnet.util.Excluder;

public class QueryConfig extends Config {

    public static transient boolean useOpenMarket = true;
    public static transient boolean useMilitaryMarket = true;
    public static transient boolean useBlackMarket = false;
    public static transient boolean useCustomMarkets = false;

    public static void configure() {
        useOpenMarket = get("queryUseOpenMarket", useOpenMarket);
        useMilitaryMarket = get("queryUseMilitaryMarket", useMilitaryMarket);
        useBlackMarket = get("queryUseBlackMarket", useBlackMarket);
        useCustomMarkets = get("queryUseCustomMarket", useCustomMarkets);
    }

    public static Filter getSubmarketFilter() {
        return Excluder.getSubmarketFilters(useOpenMarket, useMilitaryMarket, useBlackMarket, useCustomMarkets);
    }
}
