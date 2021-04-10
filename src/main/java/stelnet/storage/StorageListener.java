package stelnet.storage;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;

import stelnet.helper.GlobalHelper;
import stelnet.helper.StorageHelper;

public class StorageListener implements EconomyTickListener {

    @Override
    public void reportEconomyMonthEnd() {
    }

    @Override
    public void reportEconomyTick(int iterIndex) {
        IntelManagerAPI intelManager = GlobalHelper.getIntelManager();
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
