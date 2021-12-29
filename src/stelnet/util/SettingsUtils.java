package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ModSpecAPI;
import com.fs.starfarer.api.SettingsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import com.fs.starfarer.api.combat.ShipHullSpecAPI;
import com.fs.starfarer.api.loading.ContactTagSpec;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Provides easy access to Global.getSettings() methods.
 */
@Log4j
public class SettingsUtils {

    public static float computeStringWidth(String text) {
        return computeStringWidth(text, Fonts.DEFAULT_SMALL);
    }

    public static float computeStringWidth(String text, String font) {
        return getSettings().computeStringWidth(text, font);
    }

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

    public static ShipHullSpecAPI getHullSpec(String hullId) {
        return getSettings().getHullSpec(hullId);
    }

    public static Set<ContactTagSpec> getAllContactTypes() {
        Set<ContactTagSpec> contactSpecs = new LinkedHashSet<>();
        JSONObject types;
        try {
            types = getSettings().getMergedJSONForMod("data/config/contact_tag_data.json", "");
            @SuppressWarnings("unchecked")
            Iterator<String> iterator = types.keys();
            while (iterator.hasNext()) {
                try {
                    String tag = iterator.next();
                    JSONObject object = types.getJSONObject(tag);
                    contactSpecs.add(new ContactTagSpec(tag, null, object));
                } catch (JSONException e) {
                    log.error("Failed reading JSON content", e);
                }
            }
        } catch (IOException | JSONException e) {
            log.error("Failed reading merged JSON file", e);
        }

        return contactSpecs;
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
