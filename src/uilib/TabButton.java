package uilib;

import com.fs.starfarer.api.ui.CutStyle;
import stelnet.util.SettingsUtils;
import uilib.property.Size;

public class TabButton extends Button {

    public TabButton(String title, boolean isActive, int shortcut) {
        super(new Size(140, UiConstants.DEFAULT_ROW_HEIGHT), title, true);
        setPadding(0);
        if (isActive) {
            setBackgroundColor(SettingsUtils.getButtonHighlightColor());
        }
        setCutStyle(CutStyle.TOP);
        if (shortcut > 0) {
            setShortcut(shortcut);
        }
    }
}
