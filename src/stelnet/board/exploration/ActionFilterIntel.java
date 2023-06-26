package stelnet.board.exploration;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import stelnet.filter.Filter;
import stelnet.filter.IntelIsFaction;
import stelnet.filter.IntelIsHidden;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import stelnet.util.CollectionUtils;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;
import uilib2.intel.IntelUiAction;

public class ActionFilterIntel implements IntelUiAction {

    private final FilterFactory factory = new FilterFactory();

    @Override
    public void act(final IntelUIAPI ui) {
        final Set<Filter> filters = getFilters();
        final List<IntelInfoPlugin> intelList = ExplorationHelper.getFilterableIntel();
        setHidden(intelList, false);
        // game can have some permanently hidden intel, we don't want to count those
        CollectionUtils.reduce(intelList, new LogicalNot(new IntelIsHidden()));
        if (filters.size() > 0) {
            CollectionUtils.reduce(intelList, new LogicalOr(filters, ""));
            setFlag(intelList);
            setHidden(intelList, true);
        }
    }

    private void setFlag(final List<IntelInfoPlugin> intelList) {
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

    private void setHidden(final List<IntelInfoPlugin> intelList, final boolean isHidden) {
        for (final IntelInfoPlugin intel : intelList) {
            intel.setHidden(isHidden);
        }
    }

    private Set<Filter> getFilters() {
        final Set<Filter> filters = new LinkedHashSet<>();
        for (final ExplorationL10n key : factory.keySet()) {
            final Filter filter = factory.get(key);
            final String isEnabledKey = ExplorationHelper.getEnabledKey(key);
            final String isCheckedKey = ExplorationHelper.getCheckedKey(key);
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        for (final FactionAPI faction : ExplorationHelper.getFactions()) {
            final Filter filter = new LogicalAnd(
                Arrays.<Filter>asList(factory.get(ExplorationL10n.TYPE_RAIDING_BASE), new IntelIsFaction(faction)),
                "Raiding Faction: " + faction.getDisplayName()
            );
            final IdAware key = new PromotedFaction(faction);
            final String isEnabledKey = ExplorationHelper.getEnabledKey(key);
            final String isCheckedKey = ExplorationHelper.getCheckedKey(key);
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
