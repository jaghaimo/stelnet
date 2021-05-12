package stelnet.config;

public class MarketConfig extends Config {

    public static boolean ignoreStorageInQueries = true;
    public static boolean warnAboutEndOfMonth = true;

    public static void configure() {
        ignoreStorageInQueries = get("ignoreStorageInQueries", ignoreStorageInQueries);
        warnAboutEndOfMonth = get("warnAboutEndOfMonth", warnAboutEndOfMonth);
    }
}
