package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import stelnet.filter.Filter;
import stelnet.filter.LogicalFalse;
import stelnet.filter.LogicalTrue;
import stelnet.util.L10n;
import stelnet.util.MemoryHelper;
import uilib2.Drawable;
import uilib2.UiConstants;
import uilib2.button.BasicAreaCheckbox;
import uilib2.button.ButtonBuilder;
import uilib2.intel.ActionUpdateForItem;
import uilib2.intel.IntelCallbackBuilder;

@Log4j
@RequiredArgsConstructor
public class EnumButton implements Drawable {

    private final Map<ExplorationL10n, Filter> enumToFilterMap = new LinkedHashMap<>();

    {
        // Type missions
        enumToFilterMap.put(ExplorationL10n.TYPE_ANALYZE_MISSION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_COMM_RELAY, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_HISTORIAN_OFFER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_MEMORY_BANK, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_OTHER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_RAIDING_BASE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_STORY_MISSION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_SURVEY_MISSION, new LogicalTrue());
        // Memory banks
        enumToFilterMap.put(ExplorationL10n.BANK_DEBRIS_FIELD, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_DERELICT_SHIP, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_DOMAIN_ERA_PROBE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_HABITABLE_WORLD, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_ORBITAL_HABITAT, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_OTHER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_RUINS_LOCATION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SUPPLY_CACHE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SURVEY_DATA, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SURVEY_SHIP, new LogicalTrue());
    }

    private final Set<Filter> filters;
    private final ExplorationL10n buttonType;
    private final IntelInfoPlugin intel;
    private final float width;
    private final boolean withShift;

    @Setter
    private String memoryKeyEnabledOverwrite;

    @Override
    public void draw(TooltipMakerAPI tooltip) {
        String memoryKeyChecked = MemoryHelper.key(
            ExplorationBoard.MEMORY_PREFIX,
            buttonType,
            ExplorationBoard.MEMORY_SUFFIX_CHECKED
        );
        String memoryKeyEnabled = getMemoryKeyEnabled();
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

    private String getMemoryKeyEnabled() {
        if (memoryKeyEnabledOverwrite != null) {
            return memoryKeyEnabledOverwrite;
        }
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, buttonType, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
    }

    private BasicAreaCheckbox getButton(String memoryKeyChecked, boolean isChecked) {
        return new BasicAreaCheckbox(
            L10n.get(buttonType),
            new IntelCallbackBuilder()
                .addConfirmAction(new UpdateMemoryFlag(memoryKeyChecked, !isChecked))
                .addConfirmAction(new AddFilterAction(filters, memoryKeyChecked, getFilter()))
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

    private Filter getFilter() {
        if (enumToFilterMap.containsKey(buttonType)) {
            log.debug("Found filter for enum " + buttonType.name());
            return enumToFilterMap.get(buttonType);
        }
        log.warn("Unknown filter requested, returning false filter");
        return new LogicalFalse();
    }
}
