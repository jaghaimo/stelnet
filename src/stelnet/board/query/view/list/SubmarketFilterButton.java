package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import lombok.Setter;
import stelnet.board.query.QueryManager;
import stelnet.filter.Filter;

@Setter
public class SubmarketFilterButton extends FilteringButton {

    public SubmarketFilterButton(QueryManager manager, String title, Filter filter, float width, boolean isStateOn) {
        super(manager, title, filter, width, isStateOn);
    }

    @Override
    public void onConfirm(IntelUIAPI ui) {
        super.onConfirm(ui);
        if (isStateOn()) {
            manager.getSubmarketFilters().add(filter);
        } else {
            manager.getSubmarketFilters().remove(filter);
        }
        manager.updateIntel();
    }
}
