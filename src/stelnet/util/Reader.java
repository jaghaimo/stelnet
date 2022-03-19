package stelnet.util;

import com.fs.starfarer.api.Global;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public abstract class Reader {

    protected static List<String> getStrings(String path) {
        List<String> strings = new LinkedList<>();
        try {
            JSONArray config = Global.getSettings().getMergedSpreadsheetDataForMod("id", path, ModConstants.STELNET);
            for (int i = 0; i < config.length(); i++) {
                JSONObject row = config.getJSONObject(i);
                strings.add(row.getString("id"));
            }
        } catch (Throwable throwable) {
            log.warn("Skipping invalid file " + path, throwable);
        }
        return strings;
    }

    /**
     * Used for configuration.
     */
    public static JSONObject loadJson(String filename) {
        try {
            return Global.getSettings().loadJSON(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    /**
     * Used for translations.
     */
    public static JSONObject loadMergedJson(String filename) {
        try {
            return Global.getSettings().getMergedJSONForMod(filename, ModConstants.STELNET);
        } catch (IOException | JSONException exception) {
            return getEmptyJsonObject(exception, filename);
        }
    }

    private static JSONObject getEmptyJsonObject(Exception exception, String filename) {
        log.warn("Failed to read " + filename, exception);
        return new JSONObject();
    }
}
