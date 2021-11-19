package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.QueryManager;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class ToggleGroupingButton extends ControlButton {

    public ToggleGroupingButton(final QueryManager manager, boolean isEnabled) {
        super("Group by System", "Group by Market", isEnabled, manager.isGrouppedBySystem());
        setSize(new Size(getSize().getWidth() + 30, UiConstants.VICTOR_14_BUTTON_HEIGHT));
        setTextColor(Misc.getBasePlayerColor());
        setBackgroundColor(Misc.getDarkPlayerColor());
        setBackgroundSelectedColor(Misc.getDarkPlayerColor());
        setPadding(1);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    manager.setGrouppedBySystem(!manager.isGrouppedBySystem());
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
