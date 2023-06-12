package stelnet.board.exploration.button;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.board.exploration.ActionFilterIntel;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.exploration.IdAware;
import stelnet.board.exploration.PromotedFaction;
import stelnet.board.exploration.UpdateMemoryFlag;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.CheckboxCustom;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.actions.UpdateForItem;
import uilib2.intel.actions.UpdateIntelList;

@RequiredArgsConstructor
public class FactionButton extends ExplorationButton {

    private final FactionAPI faction;
    private final IntelInfoPlugin intel;
    private final float width;
    private final boolean withShift;

    @Override
    public UIComponentAPI draw(TooltipMakerAPI tooltip) {
        IdAware key = new PromotedFaction(faction);
        String memoryKeyChecked = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            key,
            ExplorationBoard.MEMORY_SUFFIX_CHECKED
        );
        String memoryKeyEnabled = getMemoryKeyEnabled(key);
        boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Drawable button = new ButtonBuilder(getButton(memoryKeyChecked, isChecked))
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .build();

        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        return button.draw(tooltip);
    }

    private Button getButton(String memoryKeyChecked, boolean isChecked) {
        return new CheckboxCustom(
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            Misc.ucFirst(faction.getDisplayName()),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionFilterIntel())
                .addConfirmAction(new UpdateIntelList())
                .addConfirmAction(new UpdateForItem(intel))
                .build(),
            Fonts.DEFAULT_SMALL,
            faction.getBaseUIColor(),
            UICheckboxSize.SMALL,
            UiConstants.BUTTON_PADDING
        );
    }
}
