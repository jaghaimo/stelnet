package stelnet.util;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

/**
 * Provides easy access to Global.getSector().getIntelManager() methods.
 */
public class IntelManager {

    public static void addIntel(BaseIntelPlugin plugin) {
        addIntel(plugin, false);
    }

    public static void addIntel(BaseIntelPlugin plugin, boolean forceNoMessage) {
        getIntelManager().addIntel(plugin, forceNoMessage);
    }

    public static List<IntelInfoPlugin> getAll(Class<?> className) {
        return getIntelManager().getIntel(className);
    }

    public static IntelInfoPlugin getFirstIntel(Class<?> className) {
        return getIntelManager().getFirstIntel(className);
    }

    public static void purgeIntel(Class<?> className) {
        IntelInfoPlugin plugin = getFirstIntel(className);
        while (plugin != null) {
            removeIntel(plugin);
            plugin = getFirstIntel(className);
        }
    }

    public static void removeIntel(IntelInfoPlugin plugin) {
        getIntelManager().removeIntel(plugin);
    }

    public static IntelManagerAPI getIntelManager() {
        return Global.getSector().getIntelManager();
    }
}
