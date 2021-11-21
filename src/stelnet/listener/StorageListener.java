package stelnet.listener;

import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.board.storage.StorageIntel;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;
import stelnet.util.StorageUtils;

/**
 * Adds a new intel when player closes a market, if needed.
 * Will also look for any storage intel that are no longer valid.
 */
@Log4j
public class StorageListener implements ColonyInteractionListener {

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
        List<IntelInfoPlugin> existingIntel = IntelUtils.getAll(StorageIntel.class);
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
            IntelUtils.add(plugin, true);
        }
    }

    private void removeObsolete(List<SubmarketAPI> storageSubmarkets, IntelInfoPlugin intel) {
        SubmarketAPI storage = extractStorage(intel);
        if (!storageSubmarkets.contains(storage)) {
            IntelUtils.remove(intel);
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
