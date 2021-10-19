package stelnet.util;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;

/**
 * Provides easy access to Global.getSettings() methods.
 */
public class Settings {

    public static List<ShipHullSpecAPI> getAllShipHullSpecs() {
        return getSettings().getAllShipHullSpecs();
    }

    public static int getEconomyIterPerMonth() {
        return getSettings().getInt("economyIterPerMonth");
    }

    public static String getSpriteName(String sprite) {
        return getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isDevMode() {
        return getSettings().isDevMode();
    }

    public static SettingsAPI getSettings() {
        return Global.getSettings();
    }
}
