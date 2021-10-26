package stelnet.board.storage;

import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import java.util.List;
import stelnet.util.IntelManager;
import stelnet.util.Sector;
import stelnet.util.StorageUtils;

public class StorageListener implements ColonyInteractionListener {

    public static void register() {
        ListenerManagerAPI listenerManager = Sector.getListenerManager();
        List<StorageListener> listeners = listenerManager.getListeners(StorageListener.class);
        if (listeners.isEmpty()) {
            StorageListener listener = new StorageListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        IntelManagerAPI intelManager = IntelManager.getIntelManager();
        removeAll(intelManager);
        addAll(intelManager);
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {}

    private void removeAll(IntelManagerAPI intelManager) {
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
