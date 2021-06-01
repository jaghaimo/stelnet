package stelnet.config;

public class ModConfig extends Config {

    public static transient String language = "en";
    public static transient boolean uninstallMod = false;
    public static transient boolean verboseLogging = true;

    public static void configure() {
        language = get("language", language);
        uninstallMod = get("uninstallMod", uninstallMod);
        verboseLogging = get("verboseLogging", verboseLogging);
    }
}
