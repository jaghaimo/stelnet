package stelnet.board.exploration.factory;

import com.fs.starfarer.api.impl.campaign.intel.AnalyzeEntityMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.SurveyPlanetMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.BaseHistorianOffer;
import com.fs.starfarer.api.impl.campaign.intel.bases.LuddicPathBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.bases.PirateBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.misc.BreadcrumbIntel;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.experimental.Delegate;
import stelnet.board.exploration.ExplorationL10n;
import stelnet.filter.Filter;
import stelnet.filter.IntelContainsTitle;
import stelnet.filter.IntelIsClass;
import stelnet.filter.IntelLocationHasMemory;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.settings.CaptainsLogSettings;
import stelnet.util.ModConstants;

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
        localMap.put(ExplorationL10n.TYPE_HISTORIAN_OFFER, new IntelIsClass(BaseHistorianOffer.class));
        localMap.put(ExplorationL10n.TYPE_MEMORY_BANK, new IntelIsClass(BreadcrumbIntel.class));
        localMap.put(ExplorationL10n.TYPE_RAIDING_BASE, getRaidingBaseFilter());
        localMap.put(ExplorationL10n.TYPE_SURVEY_MISSION, new IntelIsClass(SurveyPlanetMissionIntel.class));
        if (CaptainsLogSettings.COLONY_STRUCTURES.isEnabled()) {
            localMap.put(
                ExplorationL10n.TYPE_COLONY_STRUCTURE,
                getCaptainsLogFilter(new IntelContainsTitle("Structure"))
            );
        }
        if (CaptainsLogSettings.COMM_RELAYS.isEnabled()) {
            localMap.put(ExplorationL10n.TYPE_COMM_RELAY, getCaptainsLogFilter(new IntelContainsTitle("Comm Relay")));
        }
        if (CaptainsLogSettings.SALVAGEABLE.isEnabled()) {
            localMap.put(ExplorationL10n.TYPE_SALVAGEABLE, getCaptainsLogFilter(new IntelContainsTitle("Salvageable")));
        }
        if (CaptainsLogSettings.RUINS.isEnabled()) {
            localMap.put(ExplorationL10n.TYPE_ANY_RUINS, getCaptainsLogFilter(new IntelContainsTitle("Ruins")));
        }
        Filter otherFilter = getOtherFilter(localMap);
        enumToFilterMap.put(ExplorationL10n.TYPE_OTHER, otherFilter);
        enumToFilterMap.putAll(localMap);
    }

    private void addBanks() {
        Map<ExplorationL10n, Filter> localMap = new LinkedHashMap<>();
        localMap.put(ExplorationL10n.BANK_ANY_CACHE, getBankFilter("Cache"));
        localMap.put(ExplorationL10n.BANK_DEBRIS_FIELD, getBankFilter("Debris Field"));
        localMap.put(ExplorationL10n.BANK_DERELICT_SHIP, getBankFilter("Derelict Ship"));
        localMap.put(ExplorationL10n.BANK_DOMAIN_ERA_ENTITY, getBankFilter("Domain-era"));
        localMap.put(ExplorationL10n.BANK_ORBITAL_HABITAT, getBankFilter("Orbital Habitat"));
        localMap.put(ExplorationL10n.BANK_RUINS_LOCATION, getBankFilter("Ruins Location"));
        localMap.put(ExplorationL10n.BANK_SURVEY_DATA, getBankFilter("Survey Data for"));
        Filter otherFilter = new LogicalAnd(
            Arrays.asList(enumToFilterMap.get(ExplorationL10n.TYPE_MEMORY_BANK), getOtherFilter(localMap)),
            "Other Banks"
        );
        enumToFilterMap.put(ExplorationL10n.BANK_OTHER, otherFilter);
        enumToFilterMap.putAll(localMap);
    }

    private Filter getBankFilter(String title) {
        return new IntelContainsTitle(title);
    }

    private Filter getCaptainsLogFilter(Filter actualFilter) {
        return new LogicalAnd(
            Arrays.<Filter>asList(new IntelLocationHasMemory(ModConstants.CAPTAINS_LOG_INTEL), actualFilter),
            "CaptainsLog Custom Filter"
        );
    }

    private Filter getOtherFilter(Map<ExplorationL10n, Filter> localMap) {
        return new LogicalNot(new LogicalOr(localMap.values(), "Everything Else"));
    }

    private Filter getRaidingBaseFilter() {
        return new LogicalOr(
            Arrays.<Filter>asList(new IntelIsClass(LuddicPathBaseIntel.class), new IntelIsClass(PirateBaseIntel.class)),
            "Raiding Bases"
        );
    }
}
