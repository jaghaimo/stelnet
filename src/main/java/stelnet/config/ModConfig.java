package stelnet.config;

public class ModConfig extends Config {

    public static String language = "en";
    public static boolean uninstallMod = false;
    public static boolean verboseLogging = true;

    public static void configure() {
        language = get("language", language);
        uninstallMod = get("uninstallMod", uninstallMod);
        verboseLogging = get("verboseLogging", verboseLogging);
    }
}
