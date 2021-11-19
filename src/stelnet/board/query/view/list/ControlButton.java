package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import uilib.ToggleButton;
import uilib.UiConstants;
import uilib.property.Size;

public class ControlButton extends ToggleButton {

    public ControlButton(String toggledOnTitle, String toggledOffTitle, boolean isEnabled, boolean isStateOn) {
        super(
            new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT),
            toggledOnTitle,
            toggledOffTitle,
            isEnabled,
            isStateOn
        );
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
    }

    protected void scaleButton(Query query) {
        if (!query.isEnabled()) {
            setTextColor(Misc.scaleAlpha(getTextColor(), 0.3f));
            setBackgroundColor(Misc.scaleAlpha(getBackgroundColor(), 0.2f));
            setBackgroundSelectedColor(Misc.scaleAlpha(getBackgroundSelectedColor(), 0.2f));
        }
    }
}
