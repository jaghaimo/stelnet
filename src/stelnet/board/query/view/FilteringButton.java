package stelnet.board.query.view;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.filter.Filter;
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

    public FilteringButton(
        final QueryManager manager,
        final String title,
        final Filter filter,
        final float width,
        final boolean isStateOn
    ) {
        super(new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, isStateOn);
        setFilter(filter);
        setPadding(1);
        setManager(manager);
    }

    public FilteringButton(final String title, final Filter filter) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, false);
        setFilter(filter);
        setPadding(1);
    }

    public FilteringButton(final String label, final Filter filter, final String filteringButtonId) {
        this(label, filter);
        this.filteringButtonId = filteringButtonId;
    }

    public void updateVisibility(final Set<String> visibleButtonIds) {
        isVisible = visibleButtonIds.contains(filteringButtonId);
    }
}
