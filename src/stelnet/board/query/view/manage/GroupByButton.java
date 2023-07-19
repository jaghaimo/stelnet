package stelnet.board.query.view.manage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.UiConstants;
import uilib.property.Size;

public class GroupByButton extends AreaCheckbox {

    private final GroupingStrategy groupingStrategy;
    private final QueryManager manager;

    public GroupByButton(final QueryManager manager, final GroupingStrategy groupingStrategy) {
        super(
            new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_BUTTON_HEIGHT),
            L10n.query(groupingStrategy.name()),
            true,
            manager.getGroupingStrategy().equals(groupingStrategy)
        );
        this.groupingStrategy = groupingStrategy;
        this.manager = manager;
        setPadding(1);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        setStateOn(groupingStrategy.equals(manager.getGroupingStrategy()));
        super.render(tooltip);
    }

    @Override
    public void onConfirm(final IntelUIAPI ui) {
        manager.setGroupingStrategy(groupingStrategy);
        manager.updateIntel();
    }
}
