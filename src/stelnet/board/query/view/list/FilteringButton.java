package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.filter.Filter;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

@Setter
public class FilteringButton extends AreaCheckbox {

    private Filter filter;
    private QueryManager manager;

    public FilteringButton(QueryManager manager, String title, Filter filter, float width) {
        super(
            new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT),
            title,
            true,
            manager.getMarketFilters().contains(filter)
        );
        setFilter(filter);
        setPadding(1);
        setManager(manager);
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        super.onConfirm(ui);
        if (isStateOn()) {
            manager.getMarketFilters().add(filter);
        } else {
            manager.getMarketFilters().remove(filter);
        }
    }
}
