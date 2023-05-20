package stelnet.board.exploration;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.experimental.Delegate;
import stelnet.filter.Filter;
import stelnet.filter.LogicalTrue;

public class FilterFactory {

    @Delegate
    private final Map<ExplorationL10n, Filter> enumToFilterMap = new LinkedHashMap<>();

    // Type missions
    {
        enumToFilterMap.put(ExplorationL10n.TYPE_ANALYZE_MISSION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_COMM_RELAY, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_HISTORIAN_OFFER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_MEMORY_BANK, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_OTHER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_RAIDING_BASE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_STORY_MISSION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_SURVEY_MISSION, new LogicalTrue());
    }

    // Memory banks
    {
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
}
