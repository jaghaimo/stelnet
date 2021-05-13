package stelnet.l10n;

import java.util.ResourceBundle;

import stelnet.helper.LogHelper;

public abstract class Bundle {

    protected static ResourceBundle resourceBundle;

    protected Bundle(String bundle) {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle(bundle);
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
