package stelnet.board.exploration;

import com.fs.starfarer.api.util.Misc;
import stelnet.util.L10n;
import uilib2.UiConstants;
import uilib2.button.AreaCheckboxBasic;
import uilib2.button.Button;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.IntelUiAction;
import uilib2.intel.actions.UpdateIntelList;

public class TypeButton extends EnumButton {

    private final IntelUiAction intel;

    public TypeButton(
        final ExplorationL10n buttonType,
        final IntelUiAction refreshAction,
        final float width,
        final boolean withShift
    ) {
        super(buttonType, width, withShift);
        this.intel = refreshAction;
    }

    protected Button getButton(final String memoryKeyChecked, final boolean isChecked) {
        return new AreaCheckboxBasic(
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionFilterIntel())
                .addConfirmAction(new UpdateIntelList())
                .addConfirmAction(intel)
                .build(),
            Misc.getBasePlayerColor(),
            Misc.getDarkPlayerColor(),
            Misc.getBrightPlayerColor(),
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            0
        );
    }
}
