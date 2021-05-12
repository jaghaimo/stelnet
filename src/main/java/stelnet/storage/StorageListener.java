package stelnet.storage;

import java.util.List;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;

import stelnet.helper.GlobalSectorHelper;
import stelnet.helper.StorageHelper;

public class StorageListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = GlobalSectorHelper.getListenerManager();
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
        IntelManagerAPI intelManager = GlobalSectorHelper.getIntelManager();
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
        for (SubmarketAPI submarket : StorageHelper.getAllWithAccess()) {
            IntelInfoPlugin plugin = new StorageIntel(submarket);
            intelManager.addIntel(plugin, true);
        }
    }
}
