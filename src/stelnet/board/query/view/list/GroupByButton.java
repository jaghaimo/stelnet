package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryGrouping;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class GroupByButton extends ControlButton {

    public GroupByButton(final QueryManager manager, boolean isEnabled, final QueryGrouping groupingStrategy) {
        super(
            QueryL10n.GROUP_BY_SYSTEM,
            QueryL10n.GROUP_BY_MARKET,
            isEnabled,
            manager.getGroupingStrategy().equals(groupingStrategy)
        );
        setSize(new Size(getSize().getWidth() + 20, UiConstants.VICTOR_14_BUTTON_HEIGHT));
        setShortcut(Keyboard.KEY_B);
        setTextColor(Misc.getBasePlayerColor());
        setBackgroundColor(Misc.getDarkPlayerColor());
        setBackgroundSelectedColor(Misc.getDarkPlayerColor());
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
