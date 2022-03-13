package stelnet.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

@Log4j
public class L10n {

    private static transient Map<String, JSONObject> translations = new HashMap<>();

    public static void init(String filename) {
        if (!translations.containsKey(filename)) {
            String path = "translate/" + filename;
            translations.put(filename, StelnetHelper.loadMergedJson(path));
        }
    }

    public static String get(Enum<?> enumKey, Object... args) {
        String filename = enumToFilename(enumKey);
        init(filename);
        return get(filename, enumKey.name(), args);
    }

    private static String get(String filename, String key, Object... args) {
        try {
            String translation = translations.get(filename).getString(key);
            return MessageFormat.format(translation, args);
        } catch (JSONException e) {
            log.warn("Missing translation for key " + key + " in " + filename);
            return key;
        }
    }

    private static String enumToFilename(Enum<?> enumKey) {
        String enumName = enumKey.getClass().getName().toLowerCase();
        enumName = enumName.replaceAll("l10n", "");
        enumName = enumName.replaceAll("\\.", "\\_");
        enumName = removeTail(enumName);
        enumName = removeHead(enumName);
        return enumName + ".json";
    }

    private static String removeTail(String string) {
        int endIndex = string.lastIndexOf("_") + 1;
        if (endIndex > 0) {
            string = string.substring(endIndex);
        }
        return string;
    }

    private static String removeHead(String string) {
        int endIndex = string.indexOf("$");
        if (endIndex > 0) {
            string = string.substring(0, endIndex);
        }
        return string;
    }
}
