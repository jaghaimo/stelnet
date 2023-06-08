package stelnet.board.exploration.factory;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.exploration.ActionFilterIntel;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.board.exploration.FlipMatchingKeys;
import stelnet.board.exploration.button.BankButton;
import stelnet.board.exploration.button.ExplorationButton;
import stelnet.board.exploration.button.FactionButton;
import stelnet.board.exploration.button.TypeButton;
import stelnet.util.CommonL10n;
import stelnet.util.L10n;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.ButtonCustom;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.ActionUpdateIntelList;
import uilib2.intel.IntelCallbackBuilder;

@RequiredArgsConstructor
public class ButtonFactory {

    private final IntelInfoPlugin intel;
    private final float width;

    public void addBanks(List<Drawable> drawables, List<ExplorationL10n> types, String memoryKeyEnabled) {
        boolean withShift = false;
        for (ExplorationL10n type : types) {
            withShift = addButton(drawables, makeBankButton(type, withShift), withShift, memoryKeyEnabled);
        }
    }

    public void addFactions(List<Drawable> drawables, List<FactionAPI> factions, String memoryKeyEnabled) {
        boolean withShift = false;
        for (FactionAPI faction : factions) {
            withShift = addButton(drawables, makeFactionButton(faction, withShift), withShift, memoryKeyEnabled);
        }
    }

    public void addTypes(List<Drawable> drawables, List<ExplorationL10n> types, String memoryKeyEnabled) {
        boolean withShift = false;
        for (ExplorationL10n type : types) {
            withShift = addButton(drawables, makeTypeButton(type, withShift), withShift, memoryKeyEnabled);
        }
    }

    public Button getToggleButton(String enumPrefix) {
        String label = L10n.get(CommonL10n.FLIP);
        float width = Global.getSettings().computeStringWidth(label, Fonts.VICTOR_10); // minimum size to fit label
        return new ButtonBuilder(
            new ButtonCustom(
                label,
                new IntelCallbackBuilder()
                    .addConfirmAction(new FlipMatchingKeys(enumPrefix))
                    .addConfirmAction(new ActionFilterIntel())
                    .addConfirmAction(new ActionUpdateIntelList(true))
                    .addConfirmAction(new ActionUpdateForItem(intel))
                    .build(),
                Misc.getBrightPlayerColor(),
                Misc.getDarkPlayerColor(),
                Alignment.MID,
                CutStyle.C2_MENU,
                UiConstants.SPACER_DEFAULT + width + UiConstants.SPACER_SMALL, // add 5px on each side
                UiConstants.SECTION_HEADING_HEIGHT,
                0
            )
        )
            .setVictor10()
            .build();
    }

    private ExplorationButton makeBankButton(ExplorationL10n buttonType, boolean withShift) {
        return new BankButton(buttonType, intel, width, withShift);
    }

    private ExplorationButton makeFactionButton(FactionAPI faction, boolean withShift) {
        return new FactionButton(faction, intel, width, withShift);
    }

    private ExplorationButton makeTypeButton(ExplorationL10n buttonType, boolean withShift) {
        return new TypeButton(buttonType, intel, width, withShift);
    }

    private boolean addButton(
        List<Drawable> drawables,
        ExplorationButton button,
        boolean withShift,
        String memoryKeyEnabled
    ) {
        if (memoryKeyEnabled != null) {
            button.setMemoryKeyEnabledOverwrite(memoryKeyEnabled);
        }
        drawables.add(button);
        return !withShift;
    }
}
