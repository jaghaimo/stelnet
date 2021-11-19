package stelnet.config;

public class BoardConfig extends Config {

    public static transient boolean HAS_COMMODITIES = true;
    public static transient boolean HAS_MARKET = true;
    public static transient boolean HAS_STORAGE = true;

    public static void configure() {
        HAS_COMMODITIES = get("hasCommodities", HAS_COMMODITIES);
        HAS_MARKET = get("hasMarket", HAS_MARKET);
        HAS_STORAGE = get("hasStorage", HAS_STORAGE);
    }
}
