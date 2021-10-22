package stelnet.config;

import com.fs.starfarer.api.Global;
import java.io.IOException;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public abstract class Config {

    private static final transient String MOD = "stelnet";
    private static final transient String FILE = "stelnet.hjson";
    private static transient JSONObject cachedSettings = null;

    protected static JSONObject load() throws JSONException, IOException {
        if (cachedSettings == null) {
            log.debug("Reading config file");
            cachedSettings = Global.getSettings().loadJSON(FILE, MOD);
        }
        return cachedSettings;
    }

    @SuppressWarnings("unchecked")
    protected static <T> T get(String key, T defaultValue) {
        try {
            Object value = load().get(key);
            log.debug("Returning read value for '" + key + "' - " + value);
            return (T) value;
        } catch (Exception e) {
            log.error(e);
            log.warn(
                "Returning default value for '" + key + "' - " + defaultValue
            );
            return defaultValue;
        }
    }
}
