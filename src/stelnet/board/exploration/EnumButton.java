package stelnet.board.exploration;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;

@RequiredArgsConstructor
public abstract class EnumButton extends ExplorationButton {

    protected final IdAware buttonType;
    protected final float width;
    protected final boolean withShift;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final String memoryKeyChecked = ExplorationHelper.getCheckedKey(buttonType);
        final String memoryKeyEnabled = getMemoryKeyEnabled(buttonType);
        final boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        final boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Drawable button = new ButtonBuilder(getButton(memoryKeyChecked, isChecked))
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .setShortcut(buttonType.getShortcut(), false)
            .build();
        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        return button.draw(tooltip);
    }

    protected abstract Button getButton(String memoryKeyChecked, boolean isChecked);
}
