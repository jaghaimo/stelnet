package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.board.BoardDrawableInfo;
import stelnet.board.exploration.factory.ButtonFactory;
import stelnet.board.exploration.factory.TypeFactory;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.button.Button;
import uilib2.intel.DrawableIntel;
import uilib2.intel.DrawableIntelInfo;
import uilib2.label.HighlightFirst;
import uilib2.widget.HeaderWithButtons;

@Getter
@Log4j
public class ExplorationBoard extends DrawableIntel {

    public static final String MEMORY_PREFIX = "$stelnetExploration";
    public static final String MEMORY_SUFFIX_CHECKED = "Checked";
    public static final String MEMORY_SUFFIX_ENABLED = "Enabled";

    private final Set<Filter> filters = new LinkedHashSet<>();
    private final String icon = StelnetHelper.getSpriteName("exploration");
    private final IntelSortTier sortTier = IntelSortTier.TIER_0;

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        int hiddenIntelNumber = ExplorationHelper.getHiddenNumber();
        return new BoardDrawableInfo(
            L10n.get(ExplorationL10n.BOARD_TITLE),
            L10n.get(ExplorationL10n.BOARD_DESCRIPTION, hiddenIntelNumber),
            null,
            new HighlightFirst(String.valueOf(hiddenIntelNumber))
        );
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(Tags.INTEL_EXPLORATION);
        return tags;
    }

    @Override
    public void notifyPlayerAboutToOpenIntelScreen() {
        log.debug("Forcing filters upon exploration tab intel");
        new ActionFilterIntel().act(null);
    }

    @Override
    protected List<Drawable> getDrawableList(float width, float height) {
        ButtonFactory factory = new ButtonFactory(this, width);
        List<Drawable> drawables = new LinkedList<>();
        addTypes(drawables, factory);
        addFactions(drawables, factory);
        addMissions(drawables, factory);
        return drawables;
    }

    private void addHeader(List<Drawable> drawables, String title, Button toggleButton) {
        drawables.add(new HeaderWithButtons(title, Misc.getBasePlayerColor(), Misc.getDarkPlayerColor(), toggleButton));
        drawables.add(new Spacer(UiConstants.SPACER_DEFAULT));
    }

    private void addLargeSpacer(List<Drawable> drawables) {
        drawables.add(new Spacer(UiConstants.SPACER_LARGE));
    }

    private void addTypes(List<Drawable> drawables, ButtonFactory factory) {
        List<ExplorationL10n> buttonTypes = TypeFactory.getTypes();
        Button toggleButton = factory.getToggleButton("TYPE_");
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_TYPE), toggleButton);
        factory.addTypes(drawables, buttonTypes, null);
        addLargeSpacer(drawables);
    }

    private void addFactions(List<Drawable> drawables, ButtonFactory factory) {
        List<FactionAPI> factions = ExplorationHelper.getFactions();
        if (factions.isEmpty()) {
            return;
        }
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_FACTION), null);
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_RAIDING_BASE,
            MEMORY_SUFFIX_CHECKED
        );
        factory.addFactions(drawables, factions, memoryKeyEnabled);
        addLargeSpacer(drawables);
    }

    private void addMissions(List<Drawable> drawables, ButtonFactory factory) {
        List<ExplorationL10n> buttonTypes = TypeFactory.getBanks();
        Button toggleButton = factory.getToggleButton("BANK_");
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_MEMORY_BANK), toggleButton);
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_MEMORY_BANK,
            MEMORY_SUFFIX_CHECKED
        );
        factory.addTypes(drawables, buttonTypes, memoryKeyEnabled);
    }
}
