package stelnet.board.exploration.button;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;
import stelnet.board.exploration.ExplorationBoard;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;

@RequiredArgsConstructor
public abstract class EnumButton extends ExplorationButton {

    protected final ExplorationL10n buttonType;
    protected final float width;
    protected final boolean withShift;

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

    protected abstract Button getButton(String memoryKeyChecked, boolean isChecked);
}
