package stelnet.board.query.view.list;

import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Setter
public abstract class FilteringButton extends AreaCheckbox {

    protected Filter filter;
    protected QueryManager manager;

    public FilteringButton(QueryManager manager, String title, Filter filter, float width, boolean isStateOn) {
        super(new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT), title, true, isStateOn);
        setFilter(filter);
        setPadding(1);
        setManager(manager);
    }
}
