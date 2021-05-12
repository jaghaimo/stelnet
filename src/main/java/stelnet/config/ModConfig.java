package stelnet.config;

public class ModConfig extends Config {

    public static String logLevel = "DEBUG";
    public static boolean uninstallMod = false;

    public static void configure() {
        logLevel = get("logLevel", logLevel);
        uninstallMod = get("uninstallMod", uninstallMod);
    }
}
