package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.BasicAreaCheckbox;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class FactionButton implements Drawable {

    private final FactionAPI faction;
    private final IntelInfoPlugin intel;
    private final float width;
    private final boolean withShift;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        String memoryKeyChecked = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            faction,
            ExplorationBoard.MEMORY_SUFFIX_CHECKED
        );
        String memoryKeyEnabled = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            ExplorationL10n.TYPE_RAIDING_BASE,
            ExplorationBoard.MEMORY_SUFFIX_ENABLED
        );
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

    private BasicAreaCheckbox getButton(String memoryKeyChecked, boolean isChecked) {
        return new BasicAreaCheckbox(
            Misc.ucFirst(faction.getDisplayName()),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionUpdateForItem(intel))
                .build(),
            faction.getBaseUIColor(),
            faction.getDarkUIColor(),
            faction.getBrightUIColor(),
            (width - UiConstants.BUTTON_PADDING) / 2,
            UiConstants.BUTTON_HEIGHT,
            UiConstants.BUTTON_PADDING
        );
    }
}
