package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import java.util.ArrayList;
import java.util.List;
import stelnet.filter.AnyHasTag;
import stelnet.filter.FactionIsRaiding;
import stelnet.filter.IntelIsClass;
import stelnet.filter.IntelIsHidden;
import stelnet.filter.IntelLocationHasMemory;
import stelnet.filter.LogicalNot;
import stelnet.util.CollectionUtils;
import stelnet.util.MemoryHelper;
import stelnet.util.ModConstants;

public class ExplorationHelper {

    private static final String MEMORY_PREFIX = "$stelnetExploration";
    private static final String MEMORY_SUFFIX_CHECKED = "Checked";
    private static final String MEMORY_SUFFIX_ENABLED = "Enabled";

    public static String getCheckedKey(final ButtonAware entity) {
        return MemoryHelper.key(ExplorationHelper.MEMORY_PREFIX, entity, ExplorationHelper.MEMORY_SUFFIX_CHECKED);
    }

    public static String getEnabledKey(final ButtonAware entity) {
        return MemoryHelper.key(ExplorationHelper.MEMORY_PREFIX, entity, ExplorationHelper.MEMORY_SUFFIX_ENABLED);
    }

    public static List<IntelInfoPlugin> getFilterableIntel() {
        final List<IntelInfoPlugin> intelList = new ArrayList<>(Global.getSector().getIntelManager().getIntel());
        CollectionUtils.reduce(intelList, new AnyHasTag(Tags.INTEL_EXPLORATION));
        CollectionUtils.reduce(intelList, new LogicalNot(new IntelIsClass(ExplorationBoard.class)));
        return intelList;
    }

    public static int getHiddenNumber() {
        final List<IntelInfoPlugin> explorationIntel = getFilterableIntel();
        CollectionUtils.reduce(explorationIntel, new IntelLocationHasMemory(ModConstants.EXPLORATION_MANAGE));
        CollectionUtils.reduce(explorationIntel, new IntelIsHidden());
        return explorationIntel.size();
    }

    public static List<FactionAPI> getFactions() {
        final List<FactionAPI> factions = Global.getSector().getAllFactions();
        CollectionUtils.reduce(factions, new FactionIsRaiding());
        return factions;
    }
}
