package stelnet.util;

import java.text.MessageFormat;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public class L10n {

    protected static JSONObject translations;

    public static void init() {
        if (translations == null) {
            translations = SettingsUtils.load("data/config/stelnet/strings.json");
        }
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
        String key = enumToKey(enumKey);
        return get(key, args);
    }

    private static String enumToKey(Enum<?> enumKey) {
        String enumPrefix = enumKey.getClass().getName().toUpperCase();
        enumPrefix = enumPrefix.replaceAll("L10N", "");
        enumPrefix = enumPrefix.replaceAll("\\.", "\\_");
        enumPrefix = removeHead(enumPrefix);
        enumPrefix = removeTail(enumPrefix);
        return enumPrefix + "_" + enumKey.name();
    }

    private static String removeHead(String string) {
        int endIndex = string.lastIndexOf("_") + 1;
        if (endIndex > 0) {
            string = string.substring(endIndex);
        }
        return string;
    }

    private static String removeTail(String string) {
        int endIndex = string.indexOf("$");
        if (endIndex > 0) {
            string = string.substring(0, endIndex);
        }
        return string;
    }
}
