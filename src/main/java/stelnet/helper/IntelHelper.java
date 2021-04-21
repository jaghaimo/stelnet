package stelnet.helper;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class IntelHelper {

    public static void addIntel(BaseIntelPlugin plugin) {
        addIntel(plugin, false);
    }

    public static void addIntel(BaseIntelPlugin plugin, boolean forceNoMessage) {
        GlobalHelper.getIntelManager().addIntel(plugin);
    }

    public static List<IntelInfoPlugin> getAll(Class<?> className) {
        return GlobalHelper.getIntelManager().getIntel(className);
    }

    public static IntelInfoPlugin getFirstIntel(Class<?> className) {
        return GlobalHelper.getIntelManager().getFirstIntel(className);
    }

    public static void purgeIntel(Class<?> className) {
        IntelInfoPlugin plugin = getFirstIntel(className);
        while (plugin != null) {
            LogHelper.info("Removing existing intel for " + className);
            removeIntel(plugin);
            plugin = getFirstIntel(className);
        }
    }

    public static void removeIntel(IntelInfoPlugin plugin) {
        GlobalHelper.getIntelManager().removeIntel(plugin);
    }
}
