package stelnet.storage.button;

import java.awt.Color;

import com.fs.starfarer.api.util.Misc;

public class DisplayGroup extends Button {

    public final static String GROUPPED = "Group By Location";
    public final static String UNIFIED = "Disable Grouping";

    public DisplayGroup(boolean isStateOn) {
        super("", isStateOn);
    }

    @Override
    public Color getColor() {
        return Misc.getButtonTextColor();
    }

    @Override
    public String getTitle() {
        return isStateOn() ? GROUPPED : UNIFIED;
    }
}
