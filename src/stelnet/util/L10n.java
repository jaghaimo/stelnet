package stelnet.util;

import java.text.MessageFormat;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public class L10n {

    protected static JSONObject translations;

    public static void init() {
        // if (translations == null) {
        translations = SettingsUtils.load("data/config/stelnet/strings.json");
        // }
    }

    @Deprecated
    public static String get(String key, Object... args) {
        init();
        try {
            String translation = translations.getString(key);
            return MessageFormat.format(translation, args);
        } catch (JSONException e) {
            log.warn("Missing translation for key " + key);
            return key;
        }
    }

    public static String get(Enum<?> enumKey, Object... args) {
        String enumClass = enumKey.getClass().getSimpleName().replaceFirst("L10n", "").toUpperCase();
        String key = enumClass + "_" + enumKey.name();
        return get(key, args);
    }
}
