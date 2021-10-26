package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.List;

/**
 * Provides easy access to Global.getSettings() methods.
 */
public class Settings {

    public static List<ShipHullSpecAPI> getAllShipHullSpecs() {
        return getSettings().getAllShipHullSpecs();
    }

    public static List<WeaponSpecAPI> getAllWeaponSpecs() {
        return getSettings().getAllWeaponSpecs();
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
