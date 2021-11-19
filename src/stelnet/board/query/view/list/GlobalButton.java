package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import uilib.C2Button;
import uilib.UiConstants;
import uilib.property.Size;

public class GlobalButton extends C2Button {

    public GlobalButton(String title) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.VICTOR_14_BUTTON_HEIGHT), title, true);
        setCutStyle(CutStyle.C2_MENU);
        setPadding(1);
        overrideSize(30);
    }
}
