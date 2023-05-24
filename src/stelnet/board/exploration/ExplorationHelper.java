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
import stelnet.filter.LogicalNot;
import stelnet.util.CollectionUtils;

public class ExplorationHelper {

    public static List<IntelInfoPlugin> getFilterableIntel() {
        List<IntelInfoPlugin> intelList = new ArrayList<>(Global.getSector().getIntelManager().getIntel());
        CollectionUtils.reduce(intelList, new AnyHasTag(Tags.INTEL_EXPLORATION));
        CollectionUtils.reduce(intelList, new LogicalNot(new IntelIsClass(ExplorationBoard.class)));
        return intelList;
    }

    public static int getHiddenNumber() {
        List<IntelInfoPlugin> explorationIntel = getFilterableIntel();
        CollectionUtils.reduce(explorationIntel, new IntelIsHidden());
        return explorationIntel.size();
    }

    public static List<FactionAPI> getFactions() {
        List<FactionAPI> factions = Global.getSector().getAllFactions();
        CollectionUtils.reduce(factions, new FactionIsRaiding());
        return factions;
    }
}
