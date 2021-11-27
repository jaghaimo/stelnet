package stelnet.board.contact;

import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public class FilteringButton extends AreaCheckbox {

    private final Filter filter;

    public FilteringButton(String translatedString, Filter filter) {
        super(new Size(150, UiConstants.DEFAULT_BUTTON_HEIGHT), translatedString, true, false);
        this.filter = filter;
        setPadding(0);
    }
}
