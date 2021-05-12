package stelnet.config;

public class ModConfig extends Config {

    public static boolean uninstallMod = false;
    public static boolean verboseLogging = true;

    public static void configure() {
        uninstallMod = get("uninstallMod", uninstallMod);
        verboseLogging = get("verboseLogging", verboseLogging);
    }
}
