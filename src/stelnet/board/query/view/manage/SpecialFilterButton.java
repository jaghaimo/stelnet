package stelnet.board.query.view.manage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import uilib.UiConstants;

@Setter
public class SpecialFilterButton extends FilteringButton {

    public SpecialFilterButton(QueryManager manager, String title, Filter filter) {
        super(manager, title, filter, UiConstants.AUTO_WIDTH, manager.getSpecialFilters().contains(filter));
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        super.onConfirm(ui);
        if (isStateOn()) {
            manager.getSpecialFilters().add(filter);
        } else {
            manager.getSpecialFilters().remove(filter);
        }
        manager.updateIntel();
    }
}
