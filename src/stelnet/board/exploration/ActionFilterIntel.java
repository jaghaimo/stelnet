package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import stelnet.filter.AnyHasTag;
import stelnet.filter.FactionIsRaiding;
import stelnet.filter.Filter;
import stelnet.filter.IntelIsClass;
import stelnet.filter.IntelIsFaction;
import stelnet.filter.LogicalNot;
import stelnet.util.CollectionUtils;
import stelnet.util.MemoryHelper;
import uilib2.intel.IntelUiAction;

public class ActionFilterIntel implements IntelUiAction {

    private final FilterFactory factory = new FilterFactory();

    public static List<FactionAPI> getFactions() {
        List<FactionAPI> factions = Global.getSector().getAllFactions();
        CollectionUtils.reduce(factions, new FactionIsRaiding());
        return factions;
    }

    @Override
    public void act(IntelUIAPI ui) {
        Set<Filter> filters = getFilters();
        List<IntelInfoPlugin> intelList = new ArrayList<>(Global.getSector().getIntelManager().getIntel());
        CollectionUtils.reduce(intelList, new AnyHasTag(Tags.INTEL_EXPLORATION));
        setHidden(intelList, false);
        if (filters.size() > 1) {
            CollectionUtils.reduce(intelList, filters);
            setHidden(intelList, true);
        }
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
        for (FactionAPI faction : getFactions()) {
            Filter filter = new IntelIsFaction(faction);
            String isEnabledKey = getMemoryKey(faction, ExplorationBoard.MEMORY_SUFFIX_ENABLED);
            String isCheckedKey = getMemoryKey(faction, ExplorationBoard.MEMORY_SUFFIX_CHECKED);
            addIfNeeded(filters, isEnabledKey, isCheckedKey, filter);
        }
        filters.add(new LogicalNot(new IntelIsClass(ExplorationBoard.class)));
        return filters;
    }

    private void addIfNeeded(Set<Filter> filters, String isEnabledKey, String isCheckedKey, Filter filter) {
        boolean isEnabled = MemoryHelper.getBoolean(isEnabledKey, false);
        if (!isEnabled) {
            return;
        }
        boolean isChecked = MemoryHelper.getBoolean(isCheckedKey, true);
        if (!isChecked) {
            filters.add(new LogicalNot(filter));
        }
    }

    private String getMemoryKey(ExplorationL10n key, String suffix) {
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, key, suffix);
    }

    private String getMemoryKey(FactionAPI key, String suffix) {
        return MemoryHelper.key(ExplorationBoard.MEMORY_PREFIX, key, suffix);
    }
}
