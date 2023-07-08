package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.filters.Filter;
import stelnet.filters.FilterHelper;
import stelnet.filters.Not;
import stelnet.filters.Or;
import stelnet.filters.intel.IntelHasTag;
import stelnet.filters.intel.IntelIsHidden;
import stelnet.filters.intel.IntelLocationHasMemory;
import stelnet.util.MemoryManager;
import stelnet.util.ModConstants;

@Log4j
@RequiredArgsConstructor
public class ExplorationModel {

    @Getter
    private final IntelInfoPlugin intelToUpdate;

    @Getter
    private final Set<FactionAPI> factions = new HashSet<>();

    private final Set<IntelInfoPlugin> filterableIntel = new HashSet<>();

    private final Filter<IntelInfoPlugin> excludeBoardFilter;
    private final FilterFactory factory = new FilterFactory();

    public void changeIntelVisibility() {
        final Set<IntelInfoPlugin> intelSet = new HashSet<>(filterableIntel);
        log.debug("Forcing filters upon exploration tab intel");
        setHidden(intelSet, false);
        FilterHelper.reduce(intelSet, new Not<>(new IntelIsHidden()));
        final Set<Filter<IntelInfoPlugin>> filters = getFilters();
        if (filters.size() > 0) {
            FilterHelper.reduce(intelSet, new Or<>(filters, "Selected Filters"));
            setFlag(intelSet);
            setHidden(intelSet, true);
        }
    }

    public int getHiddenIntelNumber() {
        final Set<IntelInfoPlugin> intelSet = new HashSet<>(filterableIntel);
        FilterHelper.reduce(intelSet, new IntelLocationHasMemory(ModConstants.EXPLORATION_MANAGE));
        // game can have some permanently hidden intel, we don't want to count those
        FilterHelper.reduce(intelSet, new IntelIsHidden());
        return intelSet.size();
    }

    public void update(final List<IntelInfoPlugin> intelList) {
        updateIntelList(intelList);
        updateFactions();
    }

    private Set<Filter<IntelInfoPlugin>> getFilters() {
        final Set<Filter<IntelInfoPlugin>> filters = new HashSet<>();
        addTypeFilters(filters);
        addFactionFilters(filters);
        addBankFilters(filters);
        return filters;
    }

    private void addTypeFilters(final Set<Filter<IntelInfoPlugin>> filters) {
        for (final Types key : factory.types()) {
            final Filter<IntelInfoPlugin> filter = factory.getFilter(key);
            addIfNeeded(filters, key, filter);
        }
    }

    private void addFactionFilters(final Set<Filter<IntelInfoPlugin>> filters) {
        for (final FactionAPI faction : factions) {
            final Filter<IntelInfoPlugin> filter = factory.getFilter(faction);
            final ButtonAware key = new ButtonAwareFaction(faction);
            addIfNeeded(filters, key, filter);
        }
    }

    private void addBankFilters(final Set<Filter<IntelInfoPlugin>> filters) {
        for (final Banks key : factory.banks()) {
            final Filter<IntelInfoPlugin> filter = factory.getFilter(key);
            addIfNeeded(filters, key, filter);
        }
    }

    private void addIfNeeded(
        final Set<Filter<IntelInfoPlugin>> filters,
        final ButtonAware key,
        final Filter<IntelInfoPlugin> filter
    ) {
        final String isEnabledKey = key.getEnabledKey();
        final MemoryManager memoryManager = MemoryManager.getInstance();
        final boolean isEnabled = memoryManager.getBoolean(isEnabledKey, true);
        if (!isEnabled) {
            return;
        }
        final String isCheckedKey = key.getCheckedKey();
        final boolean isChecked = memoryManager.getBoolean(isCheckedKey, true);
        if (!isChecked) {
            filters.add(filter);
        }
    }

    private void setFlag(final Set<IntelInfoPlugin> intelList) {
        for (final IntelInfoPlugin intel : intelList) {
            setFlag(intel.getMapLocation(null));
        }
    }

    private void setFlag(final SectorEntityToken token) {
        if (token == null) {
            return;
        }
        final MemoryAPI memory = token.getMemoryWithoutUpdate();
        memory.set(ModConstants.EXPLORATION_MANAGE, true, 1);
    }

    private void setHidden(final Set<IntelInfoPlugin> intelList, final boolean isHidden) {
        for (final IntelInfoPlugin intel : intelList) {
            intel.setHidden(isHidden);
        }
    }

    private void updateFactions() {
        factions.clear();
        for (final IntelInfoPlugin intel : filterableIntel) {
            factions.add(intel.getFactionForUIColors());
        }
    }

    private void updateIntelList(final List<IntelInfoPlugin> intelList) {
        filterableIntel.clear();
        filterableIntel.addAll(intelList);
        FilterHelper.reduce(filterableIntel, new IntelHasTag(Tags.INTEL_EXPLORATION));
        FilterHelper.reduce(filterableIntel, excludeBoardFilter);
    }
}
