package stelnet.board.query.view.add;

import lombok.Getter;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public class FilteringButton extends AreaCheckbox {

    @Getter
    private final Filter filter;

    public FilteringButton(Enum<?> translationId, Filter filter) {
        this(L10n.get(translationId), filter);
    }

    public FilteringButton(String translatedString, Filter filter) {
        this(translatedString, filter, true);
    }

    public FilteringButton(String translatedString, Filter filter, boolean state) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT), translatedString, true, state);
        this.filter = filter;
        setPadding(0);
    }
}
