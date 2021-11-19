package stelnet.widget.viewer;

import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public abstract class FilteringButton extends AreaCheckbox {

    public FilteringButton(Enum<?> translationId) {
        super(new Size(180, UiConstants.DEFAULT_BUTTON_HEIGHT), L10n.get(translationId), true, true);
        setPadding(0);
    }
}
