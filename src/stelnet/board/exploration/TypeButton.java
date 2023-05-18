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
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionSelectItem;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class TypeButton implements Drawable {

    private final String MEMORY_PREFIX;
    private final Enum<?> buttonType;
    private final IntelInfoPlugin intel;
    private final float width;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        String memoryKeyChecked = MemoryHelper.key(MEMORY_PREFIX, buttonType, "Checked");
        String memoryKeyEnabled = MemoryHelper.key(MEMORY_PREFIX, buttonType, "Enabled");
        boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
        boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
        Button button = new ButtonBuilder(
            new BasicAreaCheckbox(
                L10n.get(buttonType),
                new IntelCallbackBuilder()
                    .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                    .addConfirmAction(new ActionSelectItem(intel))
                    .build(),
                Misc.getBasePlayerColor(),
                Misc.getDarkPlayerColor(),
                Misc.getBrightPlayerColor(),
                width,
                UiConstants.BUTTON_HEIGHT,
                UiConstants.BUTTON_PADDING
            )
        )
            .setChecked(isChecked)
            .setEnabled(isEnabled)
            .build();
        button.draw(tooltip);
    }
}
