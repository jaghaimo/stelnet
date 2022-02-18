package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;

public class ColorUtils {

    public static Color basePlayerColor() {
        return Misc.getBasePlayerColor();
    }

    public static Color buttonBgDark() {
        return Global.getSettings().getColor("buttonBgDark");
    }

    public static Color buttonGridColor() {
        return Global.getSettings().getColor("standardGridColor");
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
