package stelnet.config;

public class BoardConfig extends Config {

    public static transient boolean hasCommodities = true;
    public static transient boolean hasMarket = true;
    public static transient boolean hasStorage = true;

    public static void configure() {
        hasCommodities = get("hasCommodities", hasCommodities);
        hasMarket = get("hasMarket", hasMarket);
        hasStorage = get("hasStorage", hasStorage);
    }
}
