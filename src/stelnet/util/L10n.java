package stelnet.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utility class to translate strings.
 */
@Log4j
public class L10n {

    public static String namespace = ModConstants.STELNET_ID;
    private static transient Map<String, JSONObject> translations = new HashMap<>();

    public static void init(final String filename) {
        if (!translations.containsKey(filename)) {
            final String path = "data/l10n/" + filename + ".json";
            translations.put(filename, Reader.loadMergedJson(path, namespace));
        }
    }

    public static String common(final String key, final Object... args) {
        return get("common", key, args);
    }

    public static String commodity(final String key, final Object... args) {
        return get("commodity", key, args);
    }

    public static String contacts(final String key, final Object... args) {
        return get("contacts", key, args);
    }

    public static String exploration(final String key, final Object... args) {
        return get("exploration", key, args);
    }

    public static String query(final String key, final Object... args) {
        return get("query", key, args);
    }

    public static String storage(final String key, final Object... args) {
        return get("storage", key, args);
    }

    public static String viewer(final String key, final Object... args) {
        return get("viewer", key, args);
    }

    public static String widget(final String key, final Object... args) {
        return get("widget", key, args);
    }

    private static String get(final String filename, final String key, final Object... args) {
        init(filename);
        try {
            final String translation = translations.get(filename).getString(key);
            return MessageFormat.format(translation, args);
        } catch (final IllegalArgumentException e) {
            log.warn("Invalid message format for key " + key + " in " + filename);
            return key;
        } catch (final JSONException e) {
            log.warn("Missing translation for key " + key + " in " + filename);
            return key;
        }
    }
}
