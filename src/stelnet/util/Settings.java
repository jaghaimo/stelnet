package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.util.List;

/**
 * Provides easy access to Global.getSettings() methods.
 */
public class Settings {

    public static List<FighterWingSpecAPI> getAllFighterWingSpecs() {
        return getSettings().getAllFighterWingSpecs();
    }

    public static List<ShipHullSpecAPI> getAllShipHullSpecs() {
        return getSettings().getAllShipHullSpecs();
    }

    public static List<String> getAllSkillIds() {
        return getSettings().getSkillIds();
    }

    public static List<SpecialItemSpecAPI> getAllSpecialItemSpecs() {
        return getSettings().getAllSpecialItemSpecs();
    }

    public static List<WeaponSpecAPI> getAllWeaponSpecs() {
        return getSettings().getAllWeaponSpecs();
    }

    public static int getEconomyIterPerMonth() {
        return getSettings().getInt("economyIterPerMonth");
    }

    public static int getOfficerMaxLevel() {
        return getSettings().getInt("officerMaxLevel");
    }

    public static SkillSpecAPI getSkill(String skillId) {
        return getSettings().getSkillSpec(skillId);
    }

    public static String getSpriteName(String sprite) {
        return getSettings().getSpriteName("stelnet", sprite);
    }

    public static boolean isDevMode() {
        return getSettings().isDevMode();
    }

    private static SettingsAPI getSettings() {
        return Global.getSettings();
    }
}
