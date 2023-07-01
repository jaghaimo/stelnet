package stelnet.board.exploration;

import com.fs.starfarer.api.ui.ButtonAPI.UICheckboxSize;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.CheckboxBasic;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.IntelUiAction;
import uilib2.intel.actions.UpdateIntelList;

@RequiredArgsConstructor
public class EnumButton extends ExplorationButton {

    protected final ButtonAware buttonType;
    private final IntelUiAction refreshAction;
    protected final float width;
    protected final boolean withShift;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final String memoryKeyChecked = buttonType.getCheckedKey();
        final String memoryKeyEnabled = getMemoryKeyEnabled(buttonType);
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

    protected Button getButton(final String memoryKeyChecked, final boolean isChecked) {
        return new CheckboxBasic(
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            buttonType.getTitle(),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(refreshAction)
                .addConfirmAction(new UpdateIntelList())
                .build(),
            UICheckboxSize.SMALL,
            0
        );
    }
}
