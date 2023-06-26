package stelnet.util;

import com.fs.starfarer.api.Global;
import java.text.MessageFormat;

/**
 * Wrapper for `Global.getSettings().getStrings()` as well as other string related utilities.
 */
public class StringsHelper {

    /**
     * Managed string categories.
     */
    public enum Category {
        STELNET_COMMON,
        STELNET_CONTACTS_BOARD,
        STELNET_EXPLORATION_BOARD,
    }

    public static String get(final Category category, final String key, final Object... args) {
        final String translation = Global.getSettings().getString(category.name(), key);
        return MessageFormat.format(translation, args);
    }
}
