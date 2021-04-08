package stelnet.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class IntelHelper {

    public static void addIntel(BaseIntelPlugin plugin) {
        Global.getSector().getIntelManager().addIntel(plugin);
    }

    public static IntelInfoPlugin getFirstIntel(Class<?> className) {
        return Global.getSector().getIntelManager().getFirstIntel(className);
    }

    public static void removeIntel(IntelInfoPlugin plugin) {
        Global.getSector().getIntelManager().removeIntel(plugin);
    }
}
