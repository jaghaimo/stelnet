package stelnet.helper;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

import lombok.extern.log4j.Log4j;

@Log4j
public class IntelHelper {

    public static void addIntel(BaseIntelPlugin plugin) {
        addIntel(plugin, false);
    }

    public static void addIntel(BaseIntelPlugin plugin, boolean forceNoMessage) {
        GlobalSectorHelper.getIntelManager().addIntel(plugin);
    }

    public static List<IntelInfoPlugin> getAll(Class<?> className) {
        return GlobalSectorHelper.getIntelManager().getIntel(className);
    }

    public static IntelInfoPlugin getFirstIntel(Class<?> className) {
        return GlobalSectorHelper.getIntelManager().getFirstIntel(className);
    }

    public static void purgeIntel(Class<?> className) {
        IntelInfoPlugin plugin = getFirstIntel(className);
        while (plugin != null) {
            log.debug("Removing intel " + className);
            removeIntel(plugin);
            plugin = getFirstIntel(className);
        }
    }

    public static void removeIntel(IntelInfoPlugin plugin) {
        GlobalSectorHelper.getIntelManager().removeIntel(plugin);
    }
}
