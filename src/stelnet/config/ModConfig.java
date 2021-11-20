package stelnet.config;

public class ModConfig extends Config {

    public static transient boolean uninstallMod = false;
    public static transient boolean verboseLogging = false;

    public static void configure() {
        uninstallMod = get("uninstallMod", uninstallMod);
        verboseLogging = get("verboseLogging", verboseLogging);
    }
}
