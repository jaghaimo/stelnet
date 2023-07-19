package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import uilib.C2Button;
import uilib.UiConstants;
import uilib.property.Size;

public class GlobalButton extends C2Button {

    public GlobalButton(final String title, final float width) {
        super(new Size(width, UiConstants.VICTOR_14_BUTTON_HEIGHT), title, true);
        setCutStyle(CutStyle.C2_MENU);
        setPadding(1);
        overrideSize(40);
    }
}
