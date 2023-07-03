package uilib;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;

/**
 * Temporary color abstraction.
 *
 * Serializing `java.awt.Color` is bad, as object references will change between game builds.
 */
public class ColorHelper {

    public static Color basePlayerColor() {
        return Misc.getBasePlayerColor();
    }

    public static Color buttonBg() {
        return Global.getSettings().getColor("buttonBg");
    }

    public static Color buttonBgDark() {
        return Global.getSettings().getColor("buttonBgDark");
    }

    public static Color buttonGridColor() {
        return Global.getSettings().getColor("standardGridColor");
    }

    public static Color buttonHighlightColor() {
        // not ideal approximation
        return Misc.scaleColor(buttonBg(), 0.7f);
    }

    public static Color buttonText() {
        return Misc.getButtonTextColor();
    }

    public static Color darkPlayerColor() {
        return Misc.getDarkPlayerColor();
    }

    public static Color negativeHighlight() {
        return Misc.getNegativeHighlightColor();
    }

    public static Color positiveHighlight() {
        return Misc.getPositiveHighlightColor();
    }
}
