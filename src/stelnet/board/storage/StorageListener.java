package stelnet.board.storage;

import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;
import stelnet.util.StorageUtils;

@Log4j
public class StorageListener implements ColonyInteractionListener {

    private transient IntelManagerAPI intelManager;

    public static void register() {
        ListenerManagerAPI listenerManager = SectorUtils.getListenerManager();
        List<StorageListener> listeners = listenerManager.getListeners(StorageListener.class);
        if (listeners.isEmpty()) {
            StorageListener listener = new StorageListener();
            listenerManager.addListener(listener, true);
        }
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        intelManager = IntelUtils.getIntelManager();
        List<IntelInfoPlugin> existingIntel = intelManager.getIntel(StorageIntel.class);
        List<SubmarketAPI> storageSubmarkets = StorageUtils.getAllWithAccess();
        updateIntelList(existingIntel, storageSubmarkets);
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {}

    private void updateIntelList(List<IntelInfoPlugin> existingIntel, List<SubmarketAPI> storageSubmarkets) {
        for (SubmarketAPI storage : storageSubmarkets) {
            addMissing(existingIntel, storage);
        }
        for (IntelInfoPlugin intel : existingIntel) {
            removeObsolete(storageSubmarkets, intel);
        }
    }

    private void addMissing(List<IntelInfoPlugin> existingIntel, SubmarketAPI storage) {
        IntelInfoPlugin plugin = new StorageIntel(storage);
        if (!existingIntel.contains(plugin)) {
            intelManager.addIntel(plugin, true);
        }
    }

    private void removeObsolete(List<SubmarketAPI> storageSubmarkets, IntelInfoPlugin intel) {
        SubmarketAPI storage = extractStorage(intel);
        if (!storageSubmarkets.contains(storage)) {
            intelManager.removeIntel(intel);
        }
    }

    private SubmarketAPI extractStorage(IntelInfoPlugin intel) {
        try {
            StorageIntel storageIntel = (StorageIntel) intel;
            return storageIntel.getStorage();
        } catch (Exception exception) {
            log.warn("Could not extract storage from intel", exception);
        }
        return null;
    }
}
