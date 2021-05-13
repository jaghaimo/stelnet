package stelnet.l10n;

import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import lombok.extern.log4j.Log4j;
import stelnet.config.ModConfig;

@Log4j
public abstract class Bundle {

    protected static ResourceBundle resourceBundle;

    protected Bundle(String bundle) {
        if (resourceBundle == null) {
            Locale locale = new Locale(ModConfig.language);
            resourceBundle = PropertyResourceBundle.getBundle(bundle, locale);
        }
    }

    protected String format(String key, Object... args) {
        String translation = resourceBundle.getString(key);
        if (translation == null) {
            translation = "Missing translation for key " + key;
            log.warn(translation);
        }
        return String.format(translation, args);
    }
}
