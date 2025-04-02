package stelnet.board.query.view.manage;

import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public class BuilderBehaviorButton extends AreaCheckbox {

    public BuilderBehaviorButton(String title, boolean isStateOn) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, isStateOn);
    }
}
