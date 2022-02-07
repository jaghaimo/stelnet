package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;

public class ColorUtils {

    public static Color buttonBgDark() {
        return Global.getSettings().getColor("buttonBgDark");
    }

    public static Color buttonText() {
        return Misc.getButtonTextColor();
    }

    public static Color negativeHighlight() {
        return Misc.getNegativeHighlightColor();
    }

    public static Color positiveHighlight() {
        return Misc.getPositiveHighlightColor();
    }
}
