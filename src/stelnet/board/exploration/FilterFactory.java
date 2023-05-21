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
import stelnet.filter.IntelContainsTitle;
import stelnet.filter.IntelIsClass;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;

public class FilterFactory {

    @Delegate
    private final Map<ExplorationL10n, Filter> enumToFilterMap = new LinkedHashMap<>();

    public FilterFactory() {
        addTypes();
        addBanks();
    }

    private void addTypes() {
        Map<ExplorationL10n, Filter> localMap = new LinkedHashMap<>();
        localMap.put(ExplorationL10n.TYPE_ANALYZE_MISSION, new IntelIsClass(AnalyzeEntityMissionIntel.class));
        // enumToFilterMap.put(ExplorationL10n.TYPE_COMM_RELAY, new LogicalTrue());
        localMap.put(ExplorationL10n.TYPE_HISTORIAN_OFFER, new IntelIsClass(BaseHistorianOffer.class));
        localMap.put(ExplorationL10n.TYPE_MEMORY_BANK, new IntelIsClass(BreadcrumbIntel.class));
        localMap.put(ExplorationL10n.TYPE_RAIDING_BASE, getRaidingBaseFilter());
        localMap.put(ExplorationL10n.TYPE_STORY_MISSION, getStoryMissionFilter());
        localMap.put(ExplorationL10n.TYPE_SURVEY_MISSION, new IntelIsClass(SurveyPlanetMissionIntel.class));
        Filter otherFilter = getOtherFilter(localMap);
        enumToFilterMap.put(ExplorationL10n.BANK_OTHER, otherFilter);
        enumToFilterMap.putAll(localMap);
    }

    private void addBanks() {
        Map<ExplorationL10n, Filter> localMap = new LinkedHashMap<>();
        localMap.put(ExplorationL10n.BANK_DEBRIS_FIELD, getBankFilter("Debris Field"));
        // enumToFilterMap.put(ExplorationL10n.BANK_DERELICT_SHIP, getBankFilter());
        // enumToFilterMap.put(ExplorationL10n.BANK_DOMAIN_ERA_PROBE, getBankFilter());
        localMap.put(ExplorationL10n.BANK_EQUIPMENT_CACHE, getBankFilter("Equipment Cache"));
        localMap.put(ExplorationL10n.BANK_HABITABLE_WORLD, getBankFilter("Habitable World"));
        localMap.put(ExplorationL10n.BANK_ORBITAL_HABITAT, getBankFilter("Orbital Habitat"));
        localMap.put(ExplorationL10n.BANK_RUINS_LOCATION, getBankFilter("Ruins Location"));
        localMap.put(ExplorationL10n.BANK_SUPPLY_CACHE, getBankFilter("Supply Cache"));
        localMap.put(ExplorationL10n.BANK_SURVEY_DATA, getBankFilter("Survey Data for"));
        // enumToFilterMap.put(ExplorationL10n.BANK_SURVEY_SHIP, getBankFilter());
        Filter otherFilter = getOtherFilter(localMap);
        enumToFilterMap.put(ExplorationL10n.BANK_OTHER, otherFilter);
        enumToFilterMap.putAll(localMap);
    }

    private Filter getBankFilter(String title) {
        return new IntelContainsTitle(title);
    }

    private Filter getOtherFilter(Map<ExplorationL10n, Filter> localMap) {
        return new LogicalNot(new LogicalOr(localMap.values(), "Everything Else"));
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
