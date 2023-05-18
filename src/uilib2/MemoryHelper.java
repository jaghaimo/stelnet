package uilib2;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;

public class MemoryHelper {

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
