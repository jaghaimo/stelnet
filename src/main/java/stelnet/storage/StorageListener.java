package stelnet.storage;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stelnet.util.IntelManager;
import stelnet.util.Sector;
import stelnet.util.StorageUtils;

@Deprecated
// Rewrite using on market close / listener
public class StorageListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = Sector.getListenerManager();
        List<StorageListener> listeners = listenerManager.getListeners(StorageListener.class);
        if (listeners.isEmpty()) {
            StorageListener listener = new StorageListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
    }

    @Override
    public void reportEconomyTick(int iterIndex) {
        IntelManagerAPI intelManager = IntelManager.getIntelManager();
        removeAll(intelManager);
        addAll(intelManager);
    }

    private static void removeAll(IntelManagerAPI intelManager) {
        IntelInfoPlugin intel = intelManager.getFirstIntel(StorageIntel.class);
        while (intel != null) {
            intelManager.removeIntel(intel);
            intel = intelManager.getFirstIntel(StorageIntel.class);
        }
    }

    private static void addAll(IntelManagerAPI intelManager) {
        for (SubmarketAPI submarket : StorageUtils.getAllWithAccess()) {
            IntelInfoPlugin plugin = new StorageIntel(submarket);
            intelManager.addIntel(plugin, true);
        }
    }
}
