package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class GroupByButton extends AreaCheckbox {

    public GroupByButton(final QueryManager manager, final GroupingStrategy groupingStrategy, float width) {
        super(
            new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT),
            L10n.get(groupingStrategy),
            true,
            manager.getGroupingStrategy().equals(groupingStrategy)
        );
        setPadding(1);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.setGroupingStrategy(groupingStrategy);
                    manager.updateIntel();
                }
            }
        );
    }
}
