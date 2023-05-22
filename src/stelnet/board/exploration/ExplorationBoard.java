package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.SectorMapAPI;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
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
import uilib2.intel.DrawableIntel;
import uilib2.intel.DrawableIntelInfo;
import uilib2.label.SectionHeading;

@Getter
public class ExplorationBoard extends DrawableIntel {

    public static final String MEMORY_PREFIX = "$stelnetExploration";
    public static final String MEMORY_SUFFIX_CHECKED = "Checked";
    public static final String MEMORY_SUFFIX_ENABLED = "Enabled";

    private final Set<Filter> filters = new LinkedHashSet<>();
    private final String icon = StelnetHelper.getSpriteName("exploration");
    private final DrawableIntelInfo intelInfo = new BoardDrawableInfo(
        L10n.get(ExplorationL10n.BOARD_TITLE),
        L10n.get(ExplorationL10n.BOARD_DESCRIPTION)
    );
    private final IntelSortTier sortTier = IntelSortTier.TIER_0;

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(Tags.INTEL_EXPLORATION);
        return tags;
    }

    @Override
    protected List<Drawable> getDrawableList(float width, float height) {
        List<Drawable> drawables = new LinkedList<>();
        addTypes(drawables, width);
        addFactions(drawables, width);
        addMissions(drawables, width);
        return drawables;
    }

    private void addHeader(List<Drawable> drawables, String title) {
        drawables.add(new SectionHeading(title, Alignment.MID, UiConstants.SPACER_LARGE));
        drawables.add(new Spacer(UiConstants.SPACER_SMALL));
    }

    private void addTypes(List<Drawable> drawables, float width) {
        List<ExplorationL10n> buttonTypes = TypeFactory.getTypes();
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_TYPE));
        new ButtonFactory(this, width).addTypes(drawables, buttonTypes, null);
    }

    private void addFactions(List<Drawable> drawables, float width) {
        List<FactionAPI> factions = ActionFilterIntel.getFactions();
        if (factions.isEmpty()) {
            return;
        }
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_FACTION));
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_RAIDING_BASE,
            MEMORY_SUFFIX_CHECKED
        );
        new ButtonFactory(this, width).addFactions(drawables, factions, memoryKeyEnabled);
    }

    private void addMissions(List<Drawable> drawables, float width) {
        List<ExplorationL10n> buttonTypes = TypeFactory.getBanks();
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_MEMORY_BANK));
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_MEMORY_BANK,
            MEMORY_SUFFIX_CHECKED
        );
        new ButtonFactory(this, width).addTypes(drawables, buttonTypes, memoryKeyEnabled);
    }
}
