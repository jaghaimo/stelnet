package stelnet.config;

public class BoardConfig extends Config {

    public static boolean hasCommodities = true;
    public static boolean hasMarket = true;
    public static boolean hasStorage = true;

    public static void configure() {
        hasCommodities = get("hasCommodities", hasCommodities);
        hasMarket = get("hasMarket", hasMarket);
        hasStorage = get("hasStorage", hasStorage);
    }
}
