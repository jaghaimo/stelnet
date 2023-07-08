package stelnet.util;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * A singleton memory manager. Use setter to inject custom MemoryAPI implementation.
 */
@RequiredArgsConstructor
public class MemoryManager {

    @Setter
    private static MemoryManager instance;

    private final transient MemoryAPI memoryApi;

    private MemoryManager() {
        this(Global.getSector().getMemoryWithoutUpdate());
    }

    public static MemoryManager getInstance() {
        if (instance == null) {
            instance = new MemoryManager();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public void unset(final String key) {
        memoryApi.unset(key);
    }

    public boolean has(final String key) {
        return memoryApi.contains(key);
    }

    public String key(final String prefix, final IdAware entity, final String suffix) {
        return prefix + entity.getId() + suffix;
    }

    public void set(final String key, final Object value) {
        memoryApi.set(key, value);
    }

    public void flip(final String key) {
        memoryApi.set(key, !memoryApi.getBoolean(key));
    }

    /**
     * Returns false if memory key does not exist.
     */
    public boolean getBoolean(final String key) {
        return memoryApi.getBoolean(key);
    }

    /**
     * Returns boolean value stored in the memory key, inserting default value if it does not exist.
     */
    public boolean getBoolean(final String key, final boolean defaultValue) {
        if (!memoryApi.contains(key)) {
            set(key, defaultValue);
            return defaultValue;
        }
        return memoryApi.getBoolean(key);
    }

    public interface IdAware {
        String getId();
    }
}
