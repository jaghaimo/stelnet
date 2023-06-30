package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.board.BoardDrawableInfo;
import stelnet.filter.Filter;
import stelnet.filter.IntelIsClass;
import stelnet.filter.LogicalNot;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.IntelUiAction;
import uilib2.intel.SmallIntel;
import uilib2.intel.actions.UpdateForItem;
import uilib2.label.HighlightFirst;
import uilib2.widget.HeaderWithButtons;

@Getter
@Log4j
public class ExplorationBoard extends SmallIntel {

    private final String icon = StelnetHelper.getSpriteName("exploration");
    private final String mainTag = Tags.INTEL_EXPLORATION;
    private final IntelSortTier sortTier = IntelSortTier.TIER_0;
    private transient Filter notBoardFilter = makeNotBoardFilter();

    public void readResolve() {
        notBoardFilter = makeNotBoardFilter();
    }

    @Override
    public void notifyPlayerAboutToOpenIntelScreen() {
        log.debug("Forcing filters upon exploration tab intel");
        new FilterIntel(notBoardFilter).act(null);
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        final int hiddenIntelNumber = ExplorationHelper.getHiddenNumber(notBoardFilter);
        return new BoardDrawableInfo(
            L10n.exploration("BOARD_TITLE"),
            L10n.exploration("BOARD_DESCRIPTION", hiddenIntelNumber),
            null,
            new HighlightFirst(String.valueOf(hiddenIntelNumber))
        );
    }

    @Override
    protected List<Drawable> getDrawableList(final float width, final float height) {
        final IntelUiAction filterAction = new FilterIntel(notBoardFilter);
        final IntelUiAction refreshAction = new UpdateForItem(this);
        final ButtonFactory factory = new ButtonFactory(filterAction, refreshAction, width);
        final List<Drawable> drawables = new LinkedList<>();
        addTypes(drawables, factory);
        addFactions(drawables, factory);
        addBanks(drawables, factory);
        return drawables;
    }

    private Filter makeNotBoardFilter() {
        return new LogicalNot(new IntelIsClass(ExplorationBoard.class));
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
        final List<FactionAPI> factions = ExplorationHelper.getFactions();
        if (factions.isEmpty()) {
            return;
        }
        final String title = L10n.exploration("HEADER_FACTION");
        final String memoryKeyChecked = ExplorationHelper.getCheckedKey(Types.TYPE_RAIDING_BASE);
        addHeader(drawables, title, null);
        factory.addFactions(drawables, factions, memoryKeyChecked);
        addLargeSpacer(drawables);
    }

    private void addBanks(final List<Drawable> drawables, final ButtonFactory factory) {
        final List<ButtonAware> banks = Banks.getAll();
        final IntelUiAction flipAction = new FlipMatchingKeys(banks);
        final Button toggleButton = factory.getToggleButton(flipAction);
        final String title = L10n.exploration("HEADER_MEMORY_BANK");
        final String memoryKeyChecked = ExplorationHelper.getCheckedKey(Types.TYPE_MEMORY_BANK);
        addHeader(drawables, title, toggleButton);
        factory.addBanks(drawables, banks, memoryKeyChecked);
    }
}
