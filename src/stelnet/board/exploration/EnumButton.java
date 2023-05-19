package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.BasicAreaCheckbox;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class EnumButton implements Drawable {

    private final Enum<?> buttonType;
    private final IntelInfoPlugin intel;
    private final float width;
    private final boolean withShift;

    @Setter
    private String memoryKeyEnabledOverwrite;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        String memoryKeyChecked = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            buttonType,
            ExplorationBoard.MEMORY_SUFFIX_CHECKED
        );
        String memoryKeyEnabled = getMemoryKeyEnabled();
        boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Drawable button = new ButtonBuilder(getButton(memoryKeyChecked, isChecked))
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .build();
        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        button.draw(tooltip);
    }

    private String getMemoryKeyEnabled() {
        if (memoryKeyEnabledOverwrite != null) {
            return memoryKeyEnabledOverwrite;
        }
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, buttonType, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
    }

    private BasicAreaCheckbox getButton(String memoryKeyChecked, boolean isChecked) {
        return new BasicAreaCheckbox(
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionUpdateForItem(intel))
                .build(),
            Misc.getBasePlayerColor(),
            Misc.getDarkPlayerColor(),
            Misc.getBrightPlayerColor(),
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            UiConstants.BUTTON_PADDING
        );
    }
}
