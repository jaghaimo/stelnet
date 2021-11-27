package stelnet.board.storage;

import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyDecivListener;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.campaign.listeners.PlayerColonizationListener;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.util.IntelUtils;
import stelnet.util.SectorUtils;
import stelnet.util.StorageUtils;

/**
 * Triggers on player colony interaction, colony decivilized, and market closed.
 * Adds a new intel when player closes a market, if needed.
 * Will also look for any storage intel that are no longer valid.
 */
@Log4j
public class StorageListener implements ColonyDecivListener, ColonyInteractionListener, PlayerColonizationListener {

    public static void register() {
        ListenerManagerAPI listenerManager = SectorUtils.getListenerManager();
        List<StorageListener> listeners = listenerManager.getListeners(StorageListener.class);
        if (listeners.isEmpty()) {
            StorageListener listener = new StorageListener();
            listenerManager.addListener(listener, true);
        }
    }

    @Override
    public void reportColonyAboutToBeDecivilized(MarketAPI market, boolean fullyDestroyed) {}

    @Override
    public void reportColonyDecivilized(MarketAPI market, boolean fullyDestroyed) {
        updateNeeded();
    }

    @Override
    public void reportPlayerColonizedPlanet(PlanetAPI planet) {
        updateNeeded();
    }

    @Override
    public void reportPlayerAbandonedColony(MarketAPI colony) {
        updateNeeded();
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        updateNeeded();
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {}

    private void updateNeeded() {
        List<IntelInfoPlugin> existingIntel = IntelUtils.getAll(StorageIntel.class);
        List<SubmarketAPI> storageSubmarkets = StorageUtils.getAllWithAccess();
        addMissing(existingIntel, storageSubmarkets);
        removeObsolete(existingIntel, storageSubmarkets);
    }

    private void addMissing(List<IntelInfoPlugin> existingIntel, List<SubmarketAPI> storageSubmarkets) {
        for (SubmarketAPI storage : storageSubmarkets) {
            addMissing(existingIntel, storage);
        }
    }

    private void addMissing(List<IntelInfoPlugin> existingIntel, SubmarketAPI storage) {
        IntelInfoPlugin plugin = new StorageIntel(storage);
        if (!existingIntel.contains(plugin)) {
            IntelUtils.add(plugin, true);
        }
    }

    private void removeObsolete(List<IntelInfoPlugin> existingIntel, List<SubmarketAPI> storageSubmarkets) {
        for (int i = existingIntel.size(); i > 0; i--) {
            IntelInfoPlugin intel = existingIntel.get(i - 1);
            removeObsolete(storageSubmarkets, intel);
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
