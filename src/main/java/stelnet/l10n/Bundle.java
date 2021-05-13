package stelnet.l10n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import lombok.extern.log4j.Log4j;
import stelnet.config.ModConfig;

@Log4j
public class Bundle {

    protected static ResourceBundle resourceBundle;

    public Bundle() {
        if (resourceBundle == null) {
            Locale locale = new Locale(ModConfig.language);
            resourceBundle = PropertyResourceBundle.getBundle("strings", locale);
        }
    }

    public String format(String key, Object... args) {
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
