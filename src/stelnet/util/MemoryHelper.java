package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import stelnet.board.exploration.IdAware;

public class MemoryHelper {

    public static String key(String prefix, IdAware entity, String suffix) {
        return prefix + entity.getId() + suffix;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        if (!memoryMap.contains(key)) {
            return defaultValue;
        }
        return memoryMap.getBoolean(key);
    }

    public static void set(String key, Object value) {
        MemoryAPI memoryMap = Global.getSector().getMemoryWithoutUpdate();
        memoryMap.set(key, value);
    }
}
