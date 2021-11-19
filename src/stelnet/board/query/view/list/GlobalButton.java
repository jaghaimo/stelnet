package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import stelnet.util.L10n;
import uilib.C2Button;
import uilib.UiConstants;
import uilib.property.Size;

public class GlobalButton extends C2Button {

    public GlobalButton(Enum<?> titleEnum) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.VICTOR_14_BUTTON_HEIGHT), L10n.get(titleEnum), true);
        setCutStyle(CutStyle.C2_MENU);
        setPadding(1);
        overrideSize(20);
    }
}
