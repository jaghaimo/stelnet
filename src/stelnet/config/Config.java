package stelnet.config;

import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;
import stelnet.util.SettingsUtils;
import stelnet.util.TagConstants;

@Log4j
public abstract class Config {

    private static transient JSONObject cachedSettings = null;

    protected static JSONObject load() throws JSONException, IOException {
        if (cachedSettings == null) {
            log.debug("Reading config file");
            cachedSettings = SettingsUtils.loadJson(TagConstants.STELNET_JSON);
        }
        return cachedSettings;
    }

    @SuppressWarnings("unchecked")
    protected static <T> T get(String key, T defaultValue) {
        try {
            Object value = load().get(key);
            log.debug("Returning read value for '" + key + "' - " + value);
            return (T) value;
        } catch (Exception exception) {
            log.warn("Returning default value for '" + key + "' - " + defaultValue, exception);
            return defaultValue;
        }
    }
}
