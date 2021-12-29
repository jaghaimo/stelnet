package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.util.L10n;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class GroupByButton extends Button {

    public GroupByButton(
        final QueryManager manager,
        final GroupingStrategy groupingStrategy,
        boolean isEnabled,
        float width
    ) {
        super(
            new Size(width, UiConstants.DEFAULT_BUTTON_HEIGHT),
            L10n.get(groupingStrategy),
            manager.getGroupingStrategy().equals(groupingStrategy)
        );
        setEnabled(isEnabled);
        setTextColor(Misc.getBasePlayerColor());
        setBackgroundColor(Misc.getDarkPlayerColor());
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

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor14();
        super.render(tooltip);
        tooltip.setButtonFontDefault();
    }
}
