package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.filter.AnyHasTag;
import stelnet.filter.FactionIsRaiding;
import stelnet.filter.Filter;
import stelnet.filter.IntelIsFaction;
import stelnet.filter.IntelIsHidden;
import stelnet.filter.IntelLocationHasMemory;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.util.CollectionUtils;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;

@Log4j
@RequiredArgsConstructor
public class ExplorationModel {

    @Getter
    private final IntelInfoPlugin intelToUpdate;

    @Getter
    private final Set<FactionAPI> factions = new HashSet<>();

    private final Set<IntelInfoPlugin> filterableIntel = new HashSet<>();

    private final Filter excludeBoardFilter;
    private final FilterFactory factory = new FilterFactory();

    public void updateFactions(final List<FactionAPI> factionList) {
        factions.clear();
        factions.addAll(factionList);
        CollectionUtils.reduce(factions, new FactionIsRaiding());
    }

    public void updateIntelList(final List<IntelInfoPlugin> intelList) {
        filterableIntel.clear();
        filterableIntel.addAll(intelList);
        CollectionUtils.reduce(filterableIntel, new AnyHasTag(Tags.INTEL_EXPLORATION));
        CollectionUtils.reduce(filterableIntel, excludeBoardFilter);
    }

    public void changeIntelVisibility() {
        final Set<IntelInfoPlugin> intelSet = new HashSet<>(filterableIntel);
        log.debug("Forcing filters upon exploration tab intel");
        setHidden(intelSet, false);
        CollectionUtils.reduce(intelSet, new LogicalNot(new IntelIsHidden()));
        final Set<Filter> filters = getFilters();
        if (filters.size() > 0) {
            CollectionUtils.reduce(intelSet, new LogicalOr(filters, ""));
            setFlag(intelSet);
            setHidden(intelSet, true);
        }
    }

    public int getHiddenIntelNumber() {
        final Set<IntelInfoPlugin> intelSet = new HashSet<>(filterableIntel);
        CollectionUtils.reduce(intelSet, new IntelLocationHasMemory(ModConstants.EXPLORATION_MANAGE));
        // game can have some permanently hidden intel, we don't want to count those
        CollectionUtils.reduce(intelSet, new IntelIsHidden());
        return intelSet.size();
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

    private Set<Filter> getFilters() {
        final Set<Filter> filters = new LinkedHashSet<>();
        for (final Types key : factory.types()) {
            final Filter filter = factory.getType(key);
            final String isEnabledKey = key.getEnabledKey();
            final String isCheckedKey = key.getCheckedKey();
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        for (final FactionAPI faction : factions) {
            final Filter filter = new LogicalAnd(
                Arrays.<Filter>asList(factory.getType(Types.TYPE_RAIDING_BASE), new IntelIsFaction(faction)),
                "Raiding Faction: " + faction.getDisplayName()
            );
            final ButtonAware key = new Factions(faction);
            final String isEnabledKey = key.getEnabledKey();
            final String isCheckedKey = key.getCheckedKey();
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        return filters;
    }

    private void addIfNeeded(
        final Set<Filter> filters,
        final String isEnabledKey,
        final String isCheckedKey,
        final Filter filter
    ) {
        final boolean isEnabled = MemoryHelper.getBoolean(isEnabledKey, true);
        if (!isEnabled) {
            return;
        }
        final boolean isChecked = MemoryHelper.getBoolean(isCheckedKey, true);
        if (!isChecked) {
            filters.add(filter);
        }
    }
}
