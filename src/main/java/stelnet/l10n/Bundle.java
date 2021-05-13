package stelnet.l10n;

import java.util.Locale;
import java.util.ResourceBundle;

import stelnet.config.ModConfig;
import stelnet.helper.LogHelper;

public abstract class Bundle {

    protected static ResourceBundle resourceBundle;

    protected Bundle(String bundle) {
        if (resourceBundle == null) {
            Locale locale = new Locale(ModConfig.language);
            resourceBundle = ResourceBundle.getBundle(bundle, locale);
        }
    }

    protected String format(String key, Object... args) {
        String translation = resourceBundle.getString(key);
        if (translation == null) {
            translation = "Missing translation for key " + key;
            LogHelper.warn(translation);
        }
        return String.format(translation, args);
    }
}
