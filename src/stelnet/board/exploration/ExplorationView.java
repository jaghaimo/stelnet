package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.BoardDrawableInfo;
import stelnet.util.L10n;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.IntelUiAction;
import uilib2.intel.actions.UpdateForItem;
import uilib2.label.HighlightFirst;
import uilib2.widget.HeaderWithButtons;

@RequiredArgsConstructor
public class ExplorationView {

    private final ExplorationModel model;

    public DrawableIntelInfo getIntelInfo() {
        final int hiddenIntelNumber = model.getHiddenIntelNumber();
        return new BoardDrawableInfo(
            L10n.exploration("BOARD_TITLE"),
            L10n.exploration("BOARD_DESCRIPTION", hiddenIntelNumber),
            null,
            new HighlightFirst(String.valueOf(hiddenIntelNumber))
        );
    }

    public List<Drawable> getDrawableList(final float width) {
        final IntelUiAction refreshAction = new UpdateForItem(model.getIntelToUpdate());
        final ButtonFactory factory = new ButtonFactory(refreshAction, width);
        final List<Drawable> drawables = new LinkedList<>();
        addTypes(drawables, factory);
        addFactions(drawables, factory);
        addBanks(drawables, factory);
        return drawables;
    }

    private void addHeader(final List<Drawable> drawables, final String title, final Button toggleButton) {
        drawables.add(new HeaderWithButtons(title, Misc.getBasePlayerColor(), Misc.getDarkPlayerColor(), toggleButton));
        drawables.add(new Spacer(UiConstants.SPACER_DEFAULT));
    }

    private void addLargeSpacer(final List<Drawable> drawables) {
        drawables.add(new Spacer(UiConstants.SPACER_LARGE));
    }

    private void addTypes(final List<Drawable> drawables, final ButtonFactory factory) {
        final List<ButtonAware> types = Types.getAll();
        final IntelUiAction flipAction = new FlipMatchingKeys(types);
        final Button toggleButton = factory.getToggleButton(flipAction);
        final String title = L10n.exploration("HEADER_TYPE");
        addHeader(drawables, title, toggleButton);
        factory.addTypes(drawables, types);
        addLargeSpacer(drawables);
    }

    private void addFactions(final List<Drawable> drawables, final ButtonFactory factory) {
        final Set<FactionAPI> factions = model.getFactions();
        if (factions.isEmpty()) {
            return;
        }
        final String title = L10n.exploration("HEADER_FACTION");
        final String memoryKeyChecked = Types.TYPE_RAIDING_BASE.getCheckedKey();
        addHeader(drawables, title, null);
        factory.addFactions(drawables, factions, memoryKeyChecked);
        addLargeSpacer(drawables);
    }

    private void addBanks(final List<Drawable> drawables, final ButtonFactory factory) {
        final List<ButtonAware> banks = Banks.getAll();
        final IntelUiAction flipAction = new FlipMatchingKeys(banks);
        final Button toggleButton = factory.getToggleButton(flipAction);
        final String title = L10n.exploration("HEADER_MEMORY_BANK");
        final String memoryKeyChecked = Types.TYPE_MEMORY_BANK.getCheckedKey();
        addHeader(drawables, title, toggleButton);
        factory.addBanks(drawables, banks, memoryKeyChecked);
    }
}
