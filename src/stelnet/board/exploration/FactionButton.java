package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.CheckboxCustom;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.IntelUiAction;
import uilib2.intel.actions.UpdateIntelList;

@RequiredArgsConstructor
public class FactionButton extends ExplorationButton {

    private final FactionAPI faction;
    private final IntelUiAction refreshAction;
    private final float width;
    private final boolean withShift;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final ButtonAware key = new ExplorationFaction(faction);
        final String memoryKeyChecked = ExplorationHelper.getCheckedKey(key);
        final String memoryKeyEnabled = getMemoryKeyEnabled(key);
        final boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        final boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Drawable button = new ButtonBuilder(getButton(memoryKeyChecked, isChecked))
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .build();

        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        return button.draw(tooltip);
    }

    private Button getButton(final String memoryKeyChecked, final boolean isChecked) {
        return new CheckboxCustom(
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            Misc.ucFirst(faction.getDisplayName()),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new FilterIntel())
                .addConfirmAction(new UpdateIntelList())
                .addConfirmAction(refreshAction)
                .build(),
            Fonts.DEFAULT_SMALL,
            faction.getBaseUIColor(),
            UICheckboxSize.SMALL,
            UiConstants.BUTTON_PADDING
        );
    }
}
