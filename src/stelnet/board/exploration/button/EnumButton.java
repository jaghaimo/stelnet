package stelnet.board.exploration.button;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.board.exploration.ActionFilterIntel;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.board.exploration.UpdateMemoryFlag;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.CheckboxBasic;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.ActionUpdateIntelList;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class EnumButton extends ExplorationButton {

    private final ExplorationL10n buttonType;
    private final IntelInfoPlugin intel;
    private final float width;
    private final boolean withShift;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        String memoryKeyChecked = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            buttonType,
            ExplorationBoard.MEMORY_SUFFIX_CHECKED
        );
        String memoryKeyEnabled = getMemoryKeyEnabled(buttonType);
        boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Drawable button = new ButtonBuilder(getButton(memoryKeyChecked, isChecked))
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .setShortcut(buttonType.getShortcut(), false)
            .build();
        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        button.draw(tooltip);
    }

    private Button getButton(String memoryKeyChecked, boolean isChecked) {
        return new CheckboxBasic(
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionFilterIntel())
                .addConfirmAction(new ActionUpdateIntelList())
                .addConfirmAction(new ActionUpdateForItem(intel))
                .build(),
            UICheckboxSize.SMALL,
            0
        );
    }
}
