package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.Fonts;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.button.ButtonBuilder;
import uilib2.button.ButtonCustom;
import uilib2.intel.IntelCallbackBuilder;
import uilib2.intel.IntelUiAction;
import uilib2.intel.actions.UpdateIntelList;

@RequiredArgsConstructor
public class ButtonFactory {

    private final IntelUiAction refreshAction;
    private final float width;

    public void addBanks(
        final List<Drawable> drawables,
        final List<ButtonAware> banks,
        final String memoryKeyEnabledOverride
    ) {
        boolean withShift = false;
        for (final ButtonAware type : banks) {
            withShift = addButton(drawables, makeEnumButton(type, withShift), withShift, memoryKeyEnabledOverride);
        }
    }

    public void addFactions(
        final List<Drawable> drawables,
        final Set<FactionAPI> factions,
        final String memoryKeyEnabledOverride
    ) {
        boolean withShift = false;
        for (final FactionAPI faction : factions) {
            withShift =
                addButton(drawables, makeFactionButton(faction, withShift), withShift, memoryKeyEnabledOverride);
        }
    }

    public void addTypes(final List<Drawable> drawables, final List<ButtonAware> types) {
        boolean withShift = false;
        for (final ButtonAware type : types) {
            withShift = addButton(drawables, makeEnumButton(type, withShift), withShift, null);
        }
    }

    public Button getToggleButton(final IntelUiAction flipAction) {
        final String label = L10n.common("FLIP");
        final float width = Global.getSettings().computeStringWidth(label, Fonts.VICTOR_10); // minimum size to fit label
        return new ButtonBuilder(
            new ButtonCustom(
                label,
                new IntelCallbackBuilder()
                    .addConfirmAction(flipAction)
                    .addConfirmAction(refreshAction)
                    .addConfirmAction(new UpdateIntelList())
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

    private ExplorationButton makeEnumButton(final ButtonAware buttonType, final boolean withShift) {
        return new EnumButton(buttonType, refreshAction, width, withShift);
    }

    private ExplorationButton makeFactionButton(final FactionAPI faction, final boolean withShift) {
        return new FactionButton(faction, refreshAction, width, withShift);
    }

    private boolean addButton(
        final List<Drawable> drawables,
        final ExplorationButton button,
        final boolean withShift,
        final String memoryKeyEnabledOverride
    ) {
        if (memoryKeyEnabledOverride != null) {
            button.setMemoryKeyEnabledOverwrite(memoryKeyEnabledOverride);
        }
        drawables.add(button);
        return !withShift;
    }
}
