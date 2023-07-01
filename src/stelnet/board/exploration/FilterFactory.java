package stelnet.board.exploration;

import com.fs.starfarer.api.impl.campaign.intel.AnalyzeEntityMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.SurveyPlanetMissionIntel;
import com.fs.starfarer.api.impl.campaign.intel.bar.events.historian.BaseHistorianOffer;
import com.fs.starfarer.api.impl.campaign.intel.bases.LuddicPathBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.bases.PirateBaseIntel;
import com.fs.starfarer.api.impl.campaign.intel.misc.BreadcrumbIntel;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
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

    private final Map<Banks, Filter> bankMap = new LinkedHashMap<>();
    private final Map<Types, Filter> typeMap = new LinkedHashMap<>();

    public FilterFactory() {
        final Filter bankFilter = new IntelIsClass(BreadcrumbIntel.class);
        final Filter captainsLogFilter = new IntelLocationHasMemory(ModConstants.CAPTAINS_LOG_INTEL);
        addTypes(bankFilter, captainsLogFilter);
        addBanks(bankFilter);
    }

    public Set<Banks> banks() {
        return bankMap.keySet();
    }

    public Set<Types> types() {
        return typeMap.keySet();
    }

    public Filter getBank(final Banks key) {
        return bankMap.get(key);
    }

    public Filter getType(final Types key) {
        return typeMap.get(key);
    }

    private void addTypes(final Filter bankFilter, final Filter captainsLogFilter) {
        final Map<Types, Filter> localMap = new LinkedHashMap<>();
        localMap.put(Types.TYPE_ANALYZE_MISSION, new IntelIsClass(AnalyzeEntityMissionIntel.class));
        localMap.put(Types.TYPE_HISTORIAN_OFFER, new IntelIsClass(BaseHistorianOffer.class));
        localMap.put(Types.TYPE_MEMORY_BANK, bankFilter);
        localMap.put(Types.TYPE_RAIDING_BASE, getRaidingBaseFilter());
        localMap.put(Types.TYPE_SURVEY_MISSION, new IntelIsClass(SurveyPlanetMissionIntel.class));
        if (CaptainsLogSettings.COLONY_STRUCTURES.isEnabled()) {
            localMap.put(Types.TYPE_COLONY_STRUCTURE, getTitleFilter(captainsLogFilter, "Structure"));
        }
        if (CaptainsLogSettings.COMM_RELAYS.isEnabled()) {
            localMap.put(Types.TYPE_COMM_RELAY, getTitleFilter(captainsLogFilter, "Comm Relay"));
        }
        if (CaptainsLogSettings.SALVAGEABLE.isEnabled()) {
            localMap.put(Types.TYPE_SALVAGEABLE, getTitleFilter(captainsLogFilter, "Salvageable"));
        }
        if (CaptainsLogSettings.RUINS.isEnabled()) {
            localMap.put(Types.TYPE_ANY_RUINS, getTitleFilter(captainsLogFilter, "Ruins"));
        }
        final Filter otherFilter = getOtherFilter(localMap.values());
        typeMap.put(Types.TYPE_OTHER, otherFilter);
        typeMap.putAll(localMap);
    }

    private void addBanks(final Filter bankFilter) {
        final Map<Banks, Filter> localMap = new LinkedHashMap<>();
        localMap.put(Banks.BANK_ANY_CACHE, getTitleFilter(bankFilter, "Cache"));
        localMap.put(Banks.BANK_DEBRIS_FIELD, getTitleFilter(bankFilter, "Debris Field"));
        localMap.put(Banks.BANK_DERELICT_SHIP, getTitleFilter(bankFilter, "Derelict Ship"));
        localMap.put(Banks.BANK_DOMAIN_ERA_ENTITY, getTitleFilter(bankFilter, "Domain-era"));
        localMap.put(Banks.BANK_ORBITAL_HABITAT, getTitleFilter(bankFilter, "Orbital Habitat"));
        localMap.put(Banks.BANK_RUINS_LOCATION, getTitleFilter(bankFilter, "Ruins Location"));
        localMap.put(Banks.BANK_SURVEY_DATA, getTitleFilter(bankFilter, "Survey Data for"));
        final Filter otherFilter = new LogicalAnd(
            Arrays.asList(typeMap.get(Types.TYPE_MEMORY_BANK), getOtherFilter(localMap.values())),
            "Other Banks"
        );
        bankMap.put(Banks.BANK_OTHER, otherFilter);
        bankMap.putAll(localMap);
    }

    private Filter getTitleFilter(final Filter bankFilter, final String title) {
        return new LogicalAnd(Arrays.asList(bankFilter, new IntelContainsTitle(title)), "Compound Filter: " + title);
    }

    private Filter getOtherFilter(final Collection<Filter> filters) {
        return new LogicalNot(new LogicalOr(filters, "Compound Filter: Everything Else"));
    }

    private Filter getRaidingBaseFilter() {
        return new LogicalOr(
            Arrays.<Filter>asList(new IntelIsClass(LuddicPathBaseIntel.class), new IntelIsClass(PirateBaseIntel.class)),
            "Compound Filter: Raiding Bases"
        );
    }
}
