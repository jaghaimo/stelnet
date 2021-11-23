package stelnet.config;

import stelnet.filter.Filter;
import stelnet.util.Excluder;

public class ViewerConfig extends Config {

    public static transient boolean useOpenMarket = true;
    public static transient boolean useMilitaryMarket = true;
    public static transient boolean useBlackMarket = false;
    public static transient boolean useCustomMarkets = false;

    public static void configure() {
        useOpenMarket = get("viewerUseOpenMarket", useOpenMarket);
        useMilitaryMarket = get("viewerUseMilitaryMarket", useMilitaryMarket);
        useBlackMarket = get("viewerUseBlackMarket", useBlackMarket);
        useCustomMarkets = get("viewerUseCustomMarket", useCustomMarkets);
    }

    public static Filter getSubmarketFilter() {
        return Excluder.getSubmarketFilters(useOpenMarket, useMilitaryMarket, useBlackMarket, useCustomMarkets);
    }
}
