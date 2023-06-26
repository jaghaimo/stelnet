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
    public void act(IntelUIAPI ui) {
        Set<Filter> filters = getFilters();
        List<IntelInfoPlugin> intelList = ExplorationHelper.getFilterableIntel();
        setHidden(intelList, false);
        // game can have some permanently hidden intel, we don't want to count those
        CollectionUtils.reduce(intelList, new LogicalNot(new IntelIsHidden()));
        if (filters.size() > 0) {
            CollectionUtils.reduce(intelList, new LogicalOr(filters, ""));
            setFlag(intelList);
            setHidden(intelList, true);
        }
    }

    private void setFlag(List<IntelInfoPlugin> intelList) {
        for (IntelInfoPlugin intel : intelList) {
            setFlag(intel.getMapLocation(null));
        }
    }

    private void setFlag(SectorEntityToken token) {
        if (token == null) {
            return;
        }
        MemoryAPI memory = token.getMemoryWithoutUpdate();
        memory.set(ModConstants.EXPLORATION_MANAGE, true, 1);
    }

    private void setHidden(List<IntelInfoPlugin> intelList, boolean isHidden) {
        for (IntelInfoPlugin intel : intelList) {
            intel.setHidden(isHidden);
        }
    }

    private Set<Filter> getFilters() {
        Set<Filter> filters = new LinkedHashSet<>();
        for (ExplorationL10n key : factory.keySet()) {
            Filter filter = factory.get(key);
            String isEnabledKey = getMemoryKey(key, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
            String isCheckedKey = getMemoryKey(key, ExplorationBoard.MEMORY_SUFFIX_CHECKED);
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        for (FactionAPI faction : ExplorationHelper.getFactions()) {
            Filter filter = new LogicalAnd(
                Arrays.<Filter>asList(factory.get(ExplorationL10n.TYPE_RAIDING_BASE), new IntelIsFaction(faction)),
                "Raiding Faction"
            );
            IdAware key = new PromotedFaction(faction);
            String isEnabledKey = getMemoryKey(key, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
            String isCheckedKey = getMemoryKey(key, ExplorationBoard.MEMORY_SUFFIX_CHECKED);
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        return filters;
    }

    private void addIfNeeded(Set<Filter> filters, String isEnabledKey, String isCheckedKey, Filter filter) {
        boolean isEnabled = MemoryHelper.getBoolean(isEnabledKey, true);
        if (!isEnabled) {
            return;
        }
        boolean isChecked = MemoryHelper.getBoolean(isCheckedKey, true);
        if (!isChecked) {
            filters.add(filter);
        }
    }

    private String getMemoryKey(IdAware key, String suffix) {
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, key, suffix);
    }
}
