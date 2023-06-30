package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;

public class MemoryHelper {

    public interface IdAware {
        public String getId();
    }

    public static void flip(final String key) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        memoryMap.set(key, !memoryMap.getBoolean(key));
    }

    /**
     * Returns false if memory key does not exist.
     */
    public static boolean getBoolean(final String key) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        return memoryMap.getBoolean(key);
    }

    /**
     * Returns boolean value stored in the memory key, inserting default value if it does not exist.
     */
    public static boolean getBoolean(final String key, final boolean defaultValue) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        if (!memoryMap.contains(key)) {
            set(key, defaultValue);
        }
        return memoryMap.getBoolean(key);
    }

    public static boolean has(final String key) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        return memoryMap.contains(key);
    }

    public static String key(final String prefix, final IdAware entity, final String suffix) {
        return prefix + entity.getId() + suffix;
    }

    public static void set(final String key, final Object value) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        memoryMap.set(key, value);
    }

    public static void unset(final String key) {
        final MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        memoryMap.unset(key);
    }
}
