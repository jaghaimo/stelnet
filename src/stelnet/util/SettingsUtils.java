package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.campaign.SpecialItemSpecAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.FighterWingSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.loading.WeaponSpecAPI;
import java.io.IOException;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides easy access to Global.getSettings() methods.
 */
@Log4j
public class SettingsUtils {

    public static List<FighterWingSpecAPI> getAllFighterWingSpecs() {
        return getSettings().getAllFighterWingSpecs();
    }

    public static List<HullModSpecAPI> getAllHullModSpecs() {
        return getSettings().getAllHullModSpecs();
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
        return getSettings().getSpriteName(TagConstants.STELNET, sprite);
    }

    public static boolean isDevMode() {
        return getSettings().isDevMode();
    }

    @Deprecated
    // workaround for bug in json reader, remove in 0.95.1
    public static JSONObject loadRawJson(String filename) {
        try {
            String jsonText = getSettings().loadText(filename, TagConstants.STELNET);
            return new JSONObject(jsonText);
        } catch (IOException | JSONException e) {
            log.warn("Failed to read " + filename, e);
            return new JSONObject();
        }
    }

    public static JSONObject load(String filename) {
        try {
            return getSettings().getMergedJSONForMod(filename, TagConstants.STELNET);
        } catch (IOException | JSONException e) {
            log.warn("Failed to read " + filename, e);
            return new JSONObject();
        }
    }

    private static SettingsAPI getSettings() {
        return Global.getSettings();
    }
}
