package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.board.BoardDrawableInfo;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.intel.DrawableIntel;
import uilib2.intel.DrawableIntelInfo;
import uilib2.label.ColoredSectionHeading;

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
        drawables.add(
            new ColoredSectionHeading(
                title,
                Misc.getBasePlayerColor(),
                Misc.zeroColor,
                Alignment.MID,
                UiConstants.SPACER_LARGE
            )
        );
        drawables.add(new Spacer(UiConstants.SPACER_SMALL));
    }

    private void addTypes(List<Drawable> drawables, float width) {
        ExplorationL10n[] buttonTypes = {
            ExplorationL10n.TYPE_ANALYZE_MISSION,
            // ExplorationL10n.TYPE_COMM_RELAY,
            ExplorationL10n.TYPE_SURVEY_MISSION,
            ExplorationL10n.TYPE_STORY_MISSION,
            ExplorationL10n.TYPE_RAIDING_BASE,
            ExplorationL10n.TYPE_HISTORIAN_OFFER,
            ExplorationL10n.TYPE_MEMORY_BANK,
            // ExplorationL10n.TYPE_OTHER,
        };
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_TYPE));
        boolean withShift = false;
        for (ExplorationL10n buttonType : buttonTypes) {
            drawables.add(new EnumButton(buttonType, this, width, withShift));
            withShift = !withShift;
        }
    }

    private void addFactions(List<Drawable> drawables, float width) {
        List<FactionAPI> factions = ActionFilterIntel.getFactions();
        if (factions.isEmpty()) {
            return;
        }
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_FACTION));
        boolean withShift = false;
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_RAIDING_BASE,
            MEMORY_SUFFIX_CHECKED
        );
        for (FactionAPI faction : factions) {
            FactionButton button = new FactionButton(faction, this, width, withShift);
            withShift = addConditionalButton(drawables, button, withShift, memoryKeyEnabled);
        }
    }

    private void addMissions(List<Drawable> drawables, float width) {
        ExplorationL10n[] buttonTypes = {
            ExplorationL10n.BANK_DEBRIS_FIELD,
            ExplorationL10n.BANK_DERELICT_SHIP,
            ExplorationL10n.BANK_DOMAIN_ERA_PROBE,
            ExplorationL10n.BANK_EQUIPMENT_CACHE,
            ExplorationL10n.BANK_HABITABLE_WORLD,
            ExplorationL10n.BANK_RUINS_LOCATION,
            ExplorationL10n.BANK_ORBITAL_HABITAT,
            ExplorationL10n.BANK_SUPPLY_CACHE,
            ExplorationL10n.BANK_SURVEY_DATA,
            ExplorationL10n.BANK_SURVEY_SHIP,
            ExplorationL10n.BANK_OTHER,
        };
        addHeader(drawables, L10n.get(ExplorationL10n.HEADER_MEMORY_BANK));
        boolean withShift = false;
        String memoryKeyEnabled = MemoryHelper.key(
            MEMORY_PREFIX,
            ExplorationL10n.TYPE_MEMORY_BANK,
            MEMORY_SUFFIX_CHECKED
        );
        for (ExplorationL10n buttonType : buttonTypes) {
            EnumButton button = new EnumButton(buttonType, this, width, withShift);
            withShift = addConditionalButton(drawables, button, withShift, memoryKeyEnabled);
        }
    }

    private boolean addConditionalButton(
        List<Drawable> drawables,
        ExplorationButton button,
        boolean withShift,
        String memoryKeyEnabled
    ) {
        button.setMemoryKeyEnabledOverwrite(memoryKeyEnabled);
        drawables.add(button);
        return !withShift;
    }
}
