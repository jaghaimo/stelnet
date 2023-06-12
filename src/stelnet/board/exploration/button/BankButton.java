package stelnet.board.exploration.button;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import stelnet.board.exploration.ActionFilterIntel;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.board.exploration.UpdateMemoryFlag;
import stelnet.util.L10n;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.CheckboxBasic;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.actions.UpdateForItem;
import uilib2.intel.actions.UpdateIntelList;

public class BankButton extends EnumButton {

    private final IntelInfoPlugin intel;

    public BankButton(ExplorationL10n buttonType, IntelInfoPlugin intel, float width, boolean withShift) {
        super(buttonType, width, withShift);
        this.intel = intel;
    }

    protected Button getButton(String memoryKeyChecked, boolean isChecked) {
        return new CheckboxBasic(
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionFilterIntel())
                .addConfirmAction(new UpdateIntelList())
                .addConfirmAction(new UpdateForItem(intel))
                .build(),
            UICheckboxSize.SMALL,
            0
        );
    }
}
