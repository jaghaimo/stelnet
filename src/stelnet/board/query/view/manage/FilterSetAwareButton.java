package stelnet.board.query.view.manage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import uilib.UiConstants;

@Setter
public class FilterSetAwareButton extends FilteringButton {

    private final Set<Filter> filterSet;

    public FilterSetAwareButton(QueryManager manager, String title, Filter filter, Set<Filter> filterSet) {
        super(manager, title, filter, UiConstants.AUTO_WIDTH, filterSet.contains(filter));
        this.filterSet = filterSet;
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        super.onConfirm(ui);
        if (isStateOn()) {
            filterSet.add(filter);
        } else {
            filterSet.remove(filter);
        }
        manager.updateIntel();
    }
}
