package stelnet.repository;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import lombok.experimental.Delegate;

/**
 * Provides easy access to memory map via concrete implementations.
 */
public abstract class Repository<T> extends PersistableMap<T> {

    private final String key;

    @Delegate
    private PersistableMap<T> localCopy;

    public Repository(String key) {
        this.key = key;
        load();
    }

    @SuppressWarnings(value = "unchecked")
    public void load() {
        try {
            localCopy = (PersistableMap<T>) getMemory().get(key);
        } catch (ClassCastException e) {
            reset();
        }
    }

    public void reset() {
        localCopy = new PersistableMap<T>();
        save();
    }

    public void save() {
        getMemory().set(key, localCopy);
    }

    private MemoryAPI getMemory() {
        return Global.getSector().getMemoryWithoutUpdate();
    }
}
