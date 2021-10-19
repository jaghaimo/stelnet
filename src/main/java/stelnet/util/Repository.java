package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import lombok.extern.log4j.Log4j;

/**
 * Provides easy access to memory map.
 */
@Log4j
public class Repository {

    @SuppressWarnings(value = "unchecked")
    public static <T> T get(String key) {
        try {
            Object object = getMemory().get(key);
            return (T) object;
        } catch (Exception e) {
            log.error("Failed to get object", e);
        }
        return null;
    }

    public static <T> T get(String key, T defaultValue) {
        T value = get(key);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public static boolean has(String key) {
        return getMemory().contains(key);
    }

    public static <T> void put(String key, T value, boolean isPersistent) {
        if (isPersistent) {
            getMemory().set(key, value);
        } else {
            getMemory().set(key, value, 0);
        }
    }

    public void unset(String key) {
        getMemory().unset(key);
    }

    private static MemoryAPI getMemory() {
        return Global.getSector().getMemoryWithoutUpdate();
    }
}
