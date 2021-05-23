package stelnet.ui;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.util.Misc;

public class TabButton extends Button {

    public TabButton(String title, boolean isActive, int shortcut) {
        super(new Size(140, 22), title, true, Misc.getGrayColor());
        if (isActive) {
            setColor(Misc.getButtonTextColor());
        }
        setCutStyle(CutStyle.TOP);
        if (shortcut > 0) {
            setShortcut(shortcut);
        }
    }
}
