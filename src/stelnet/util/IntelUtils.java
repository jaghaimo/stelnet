package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import java.util.List;

/**
 * Provides easy access to Global.getSector().getIntelManager() methods.
 */
public class IntelUtils {

    public static void add(IntelInfoPlugin plugin) {
        add(plugin, false);
    }

    public static void add(IntelInfoPlugin plugin, boolean forceNoMessage) {
        getIntelManager().addIntel(plugin, forceNoMessage);
    }

    public static List<IntelInfoPlugin> getAll(Class<?> className) {
        return getIntelManager().getIntel(className);
    }

    public static IntelInfoPlugin getFirst(Class<?> className) {
        return getIntelManager().getFirstIntel(className);
    }

    public static boolean has(IntelInfoPlugin intel) {
        return getIntelManager().hasIntel(intel);
    }

    public static void remove(IntelInfoPlugin plugin) {
        getIntelManager().removeIntel(plugin);
    }

    public static void removeAll(Class<?> className) {
        IntelInfoPlugin plugin = getFirst(className);
        while (plugin != null) {
            remove(plugin);
            plugin = getFirst(className);
        }
    }

    private static IntelManagerAPI getIntelManager() {
        return Global.getSector().getIntelManager();
    }
}
