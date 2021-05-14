package stelnet;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import lombok.extern.log4j.Log4j;
import stelnet.config.ModConfig;

@Log4j
public class L10n {

    protected static ResourceBundle resourceBundle;

    public static void init() {
        if (resourceBundle == null) {
            Locale locale = new Locale(ModConfig.language);
            resourceBundle = PropertyResourceBundle.getBundle("strings", locale);
        }
    }

    public static String get(String key, Object... args) {
        init();
        String translation;
        try {
            translation = resourceBundle.getString(key);
            return String.format(translation, args);
        } catch (MissingResourceException e) {
            log.warn("Missing translation for key " + key);
            return key;
        }
    }
}
