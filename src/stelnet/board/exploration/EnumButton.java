package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.BasicAreaCheckbox;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.ActionUpdateIntelList;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class EnumButton extends ExplorationButton implements Drawable {

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
            .build();
        if (withShift) {
            button = new ShiftedButton(button, width);
        }
        button.draw(tooltip);
    }

    private BasicAreaCheckbox getButton(String memoryKeyChecked, boolean isChecked) {
        return new BasicAreaCheckbox(
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new ActionFilterIntel())
                .addConfirmAction(new ActionUpdateIntelList(true))
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
