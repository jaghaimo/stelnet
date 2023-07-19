package stelnet.board.query.view;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import uilib.Button;
import uilib.EventHandler;
import uilib.UiConstants;
import uilib.property.Size;

public class SelectDeselectButton extends Button {

    public SelectDeselectButton(
        final String label,
        final boolean isEnabled,
        final boolean desiredState,
        final FilteringButton[] buttons
    ) {
        super(new Size(UiConstants.AUTO_WIDTH, UiConstants.DEFAULT_ROW_HEIGHT), label, isEnabled);
        setCutStyle(CutStyle.C2_MENU);
        setPadding(0);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    for (final FilteringButton button : buttons) {
                        button.setStateOn(desiredState);
                    }
                }
            }
        );
        if (!isEnabled) {
            setTextColor(Misc.scaleAlpha(getTextColor(), 0));
            setBackgroundColor(Misc.scaleAlpha(getBackgroundColor(), 0));
        }
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        super.render(tooltip);
        tooltip.setButtonFontDefault();
    }
}
