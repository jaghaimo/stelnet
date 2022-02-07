package stelnet.board.query.view;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
@Setter
public class FilteringButton extends AreaCheckbox {

    protected boolean isVisible = true;
    protected String filteringButtonId;
    protected Filter filter;
    protected QueryManager manager;

    public FilteringButton(QueryManager manager, String title, Filter filter, float width, boolean isStateOn) {
        super(new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, isStateOn);
        setFilter(filter);
        setPadding(1);
        setManager(manager);
    }

    public FilteringButton(Enum<?> translationId, Filter filter) {
        this(L10n.get(translationId), filter);
    }

    public FilteringButton(String translatedString, Filter filter) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT), translatedString, true, false);
        setFilter(filter);
        setPadding(1);
    }

    public FilteringButton(String translatedString, Filter filter, String filteringButtonId) {
        this(translatedString, filter);
        this.filteringButtonId = filteringButtonId;
    }

    public void updateVisibility(Set<String> visibleButtonIds) {
        isVisible = visibleButtonIds.contains(filteringButtonId);
    }
}
