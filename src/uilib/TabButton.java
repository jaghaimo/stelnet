package uilib;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.util.Misc;
import uilib.property.Size;

public class TabButton extends Button {

    public TabButton(String title, boolean isActive, int shortcut) {
        super(new Size(140, 22), title, true);
        if (isActive) {
            // not ideal
            setBackgroundColor(Misc.scaleColor(Global.getSettings().getColor("buttonBg"), 0.7f));
        }
        setCutStyle(CutStyle.TOP);
        if (shortcut > 0) {
            setShortcut(shortcut);
        }
    }
}
