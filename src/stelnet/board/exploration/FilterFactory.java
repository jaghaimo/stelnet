package stelnet.board.exploration;

import com.fs.starfarer.api.impl.campaign.intel.AnalyzeEntityMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.SurveyPlanetMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.PlanetaryShieldIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.ScientistAICoreIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.BaseHistorianOffer;
import com.fs.starfarer.api.impl.campaign.intel.bases.LuddicPathBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.bases.PirateBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.misc.BreadcrumbIntel;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.experimental.Delegate;
import stelnet.filter.Filter;
import stelnet.filter.IntelIsClass;
import stelnet.filter.LogicalOr;
import stelnet.filter.LogicalTrue;

public class FilterFactory {

    @Delegate
    private final Map<ExplorationL10n, Filter> enumToFilterMap = new LinkedHashMap<>();

    // Type missions
    {
        enumToFilterMap.put(ExplorationL10n.TYPE_ANALYZE_MISSION, new IntelIsClass(AnalyzeEntityMissionIntel.class));
        // enumToFilterMap.put(ExplorationL10n.TYPE_COMM_RELAY, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_HISTORIAN_OFFER, new IntelIsClass(BaseHistorianOffer.class));
        enumToFilterMap.put(ExplorationL10n.TYPE_MEMORY_BANK, new IntelIsClass(BreadcrumbIntel.class));
        // enumToFilterMap.put(ExplorationL10n.TYPE_OTHER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.TYPE_RAIDING_BASE, getRaidingBaseFilter());
        enumToFilterMap.put(ExplorationL10n.TYPE_STORY_MISSION, getStoryMissionFilter());
        enumToFilterMap.put(ExplorationL10n.TYPE_SURVEY_MISSION, new IntelIsClass(SurveyPlanetMissionIntel.class));
    }

    // Memory banks
    {
        enumToFilterMap.put(ExplorationL10n.BANK_DEBRIS_FIELD, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_DERELICT_SHIP, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_DOMAIN_ERA_PROBE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_HABITABLE_WORLD, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_ORBITAL_HABITAT, new LogicalTrue());
        // enumToFilterMap.put(ExplorationL10n.BANK_OTHER, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_RUINS_LOCATION, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SUPPLY_CACHE, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SURVEY_DATA, new LogicalTrue());
        enumToFilterMap.put(ExplorationL10n.BANK_SURVEY_SHIP, new LogicalTrue());
    }

    private Filter getRaidingBaseFilter() {
        Filter filters[] = { new IntelIsClass(LuddicPathBaseIntel.class), new IntelIsClass(PirateBaseIntel.class) };
        return new LogicalOr(Arrays.asList(filters), "Raiding Bases");
    }

    private Filter getStoryMissionFilter() {
        Filter filters[] = {
            new IntelIsClass(PlanetaryShieldIntel.class),
            new IntelIsClass(ScientistAICoreIntel.class),
        };
        return new LogicalOr(Arrays.asList(filters), "Story Missions");
    }
}
