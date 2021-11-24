package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ModSpecAPI;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
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

    public static PersonAPI createPerson() {
        return getSettings().createPerson();
    }

    public static List<String> getAllSkillIds() {
        return getSettings().getSkillIds();
    }

    public static Color getButtonHighlightColor() {
        // not ideal approximation
        return Misc.scaleColor(getColor("buttonBg"), 0.7f);
    }

    public static Color getColor(String colorId) {
        return getSettings().getColor(colorId);
    }

    public static int getEconomyIterPerMonth() {
        return getSettings().getInt("economyIterPerMonth");
    }

    public static List<ModSpecAPI> getEnabledMods() {
        return getSettings().getModManager().getEnabledModsCopy();
    }

    public static HullModSpecAPI getHullModSpec(String hullModId) {
        return getSettings().getHullModSpec(hullModId);
    }

    public static int getOfficerMaxLevel() {
        return getSettings().getInt("officerMaxLevel");
    }

    public static SkillSpecAPI getSkill(String skillId) {
        return getSettings().getSkillSpec(skillId);
    }

    public static String getSpriteName(String sprite) {
        return getSettings().getSpriteName(ModConstants.STELNET, sprite);
    }

    public static boolean isDevMode() {
        return getSettings().isDevMode();
    }

    /**
     * @Deprecated Use load() in 0.95.1 (currently bugged)
     */
    public static JSONObject loadRawJson(String filename) {
        try {
            String jsonText = getSettings().loadText(filename, ModConstants.STELNET);
            return new JSONObject(jsonText);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    public static JSONObject loadJson(String filename) {
        try {
            return getSettings().loadJSON(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    public static JSONObject loadMergedJson(String filename) {
        try {
            return getSettings().getMergedJSONForMod(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    private static JSONObject getEmptyJsonObject(Exception exception, String filename) {
        log.warn("Failed to read " + filename, exception);
        return new JSONObject();
    }

    private static SettingsAPI getSettings() {
        return Global.getSettings();
    }
}
