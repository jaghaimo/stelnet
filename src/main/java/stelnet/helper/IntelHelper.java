package stelnet.helper;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class IntelHelper {

    public static void addIntel(BaseIntelPlugin plugin) {
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
        if (plugin != null) {
            LogHelper.info("Removing existing intel for " + className);
            removeIntel(plugin);
        }
    }

    public static void removeIntel(IntelInfoPlugin plugin) {
        GlobalHelper.getIntelManager().removeIntel(plugin);
    }
}
