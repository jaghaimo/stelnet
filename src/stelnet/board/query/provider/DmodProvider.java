package stelnet.board.query.provider;

import com.fs.starfarer.api.impl.campaign.DModManager;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.Collections;
import java.util.List;

public class DmodProvider {

    private static transient List<HullModSpecAPI> allDMods;

    public static void resetCache() {
        allDMods = null;
    }

    public List<HullModSpecAPI> getDMods() {
        if (allDMods == null) {
            allDMods = DModManager.getModsWithTags(Tags.HULLMOD_DMOD);
            Collections.sort(allDMods, new ShipHullSpecSorter());
        }
        return allDMods;
    }
}
