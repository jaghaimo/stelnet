package stelnet.widget.viewer;

import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public abstract class FilteringButton extends AreaCheckbox {

    public FilteringButton(final String title) {
        super(new Size(180, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, true);
        setPadding(0);
    }
}
