package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.FactionIsRaiding;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import uilib.UiConstants;
import uilib2.Drawable;
import uilib2.MemoryHelper;
import uilib2.Spacer;
import uilib2.button.BasicAreaCheckbox;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionSelectItem;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.label.SectionHeading;

@RequiredArgsConstructor
public class ExplorationState {

    private final IntelInfoPlugin intel;

    private void addTypes(List<Drawable> drawables, float width, float height) {
        ExplorationL10n[] buttonTypes = {
            ExplorationL10n.TYPE_BASES,
            ExplorationL10n.TYPE_MEMORY_BANKS,
            ExplorationL10n.TYPE_MISSIONS,
            ExplorationL10n.TYPE_OTHER,
        };
        drawables.add(
            new SectionHeading(L10n.get(ExplorationL10n.HEADER_TYPE), Alignment.MID, UiConstants.DEFAULT_BUTTON_PADDING)
        );
        for (ExplorationL10n buttonType : buttonTypes) {
            String memoryKey = "$stelnetExploration" + buttonType.name();
            String memoryKeyChecked = memoryKey + "Checked";
            String memoryKeyEnabled = memoryKey + "Enabled";
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
                    height,
                    UiConstants.DEFAULT_BUTTON_PADDING
                )
            )
                .setChecked(isChecked)
                .setEnabled(isEnabled)
                .build();
            drawables.add(button);
        }
        addSpacer(drawables);
    }

    private void addFactions(List<Drawable> drawables, float width, float height) {
        List<FactionAPI> factions = Global.getSector().getAllFactions();
        CollectionUtils.reduce(factions, new FactionIsRaiding());
        if (factions.isEmpty()) {
            return;
        }
        drawables.add(
            new SectionHeading(
                L10n.get(ExplorationL10n.HEADER_FACTION),
                Alignment.MID,
                UiConstants.DEFAULT_BUTTON_PADDING
            )
        );
        for (FactionAPI faction : factions) {
            String memoryKey = "$stelnetExploration" + faction.getId();
            String memoryKeyChecked = memoryKey + "Checked";
            String memoryKeyEnabled = memoryKey + "Enabled";
            boolean isChecked = MemoryHelper.getBoolean(memoryKeyChecked, true);
            boolean isEnabled = MemoryHelper.getBoolean(memoryKeyEnabled, true);
            Button button = new ButtonBuilder(
                new BasicAreaCheckbox(
                    Misc.ucFirst(faction.getDisplayName()),
                    new IntelCallbackBuilder()
                        .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                        .addConfirmAction(new ActionSelectItem(intel))
                        .build(),
                    faction.getBaseUIColor(),
                    faction.getDarkUIColor(),
                    faction.getBrightUIColor(),
                    width,
                    height,
                    UiConstants.DEFAULT_BUTTON_PADDING
                )
            )
                .setChecked(isChecked)
                .setEnabled(isEnabled)
                .build();
            drawables.add(button);
        }
        addSpacer(drawables);
    }

    private void addMissions(List<Drawable> renderables, float width, float height) {}

    private void addSpacer(List<Drawable> drawable) {
        drawable.add(new Spacer(UiConstants.DEFAULT_SPACER));
    }

    public List<Drawable> toDrawableList(float width, float heigth2) {
        float height = UiConstants.DEFAULT_BUTTON_HEIGHT;
        List<Drawable> drawables = new LinkedList<>();
        addTypes(drawables, width, height);
        addFactions(drawables, width, height);
        addMissions(drawables, width, height);
        return drawables;
    }
}
