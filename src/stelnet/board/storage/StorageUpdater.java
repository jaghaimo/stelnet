package stelnet.board.storage;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyDecivListener;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.PlayerColonizationListener;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.util.StelnetHelper;

/**
 * Triggers on player colony interaction, colony decivilized, and market closed.
 * Adds a new intel when player closes a market, if needed.
 * Will also look for any storage intel that are no longer valid.
 */
@Log4j
public class StorageUpdater implements ColonyDecivListener, ColonyInteractionListener, PlayerColonizationListener {

    private static transient StorageUpdater instance;

    public static void register() {
        if (instance == null) {
            instance = new StorageUpdater();
        }
        Global.getSector().getListenerManager().addListener(instance, true);
        updateNeeded();
        log.debug("Enabled Storage Updater script");
    }

    public static void unregister() {
        if (instance != null) {
            Global.getSector().getListenerManager().removeListener(instance);
            instance = null;
            log.debug("Disabled Storage Updater script");
        }
    }

    private static void updateNeeded() {
        final List<IntelInfoPlugin> existingIntel = Global.getSector().getIntelManager().getIntel(StorageIntel.class);
        final Set<SubmarketAPI> storageSubmarkets = StelnetHelper.getAllWithAccess();
        addMissing(existingIntel, storageSubmarkets);
        removeObsolete(existingIntel, storageSubmarkets);
    }

    private static void addMissing(
        final List<IntelInfoPlugin> existingIntel,
        final Set<SubmarketAPI> storageSubmarkets
    ) {
        for (final SubmarketAPI storage : storageSubmarkets) {
            addMissing(existingIntel, storage);
        }
    }

    private static void addMissing(final List<IntelInfoPlugin> existingIntel, final SubmarketAPI storage) {
        final IntelInfoPlugin plugin = new StorageIntel(storage);
        if (!existingIntel.contains(plugin)) {
            Global.getSector().getIntelManager().addIntel(plugin, true);
        }
    }

    private static void removeObsolete(
        final List<IntelInfoPlugin> existingIntel,
        final Set<SubmarketAPI> storageSubmarkets
    ) {
        for (int i = existingIntel.size(); i > 0; i--) {
            final IntelInfoPlugin intel = existingIntel.get(i - 1);
            removeObsolete(storageSubmarkets, intel);
        }
    }

    private static void removeObsolete(final Set<SubmarketAPI> storageSubmarkets, final IntelInfoPlugin intel) {
        final SubmarketAPI storage = extractStorage(intel);
        if (!storageSubmarkets.contains(storage)) {
            Global.getSector().getIntelManager().removeIntel(intel);
        }
    }

    private static SubmarketAPI extractStorage(final IntelInfoPlugin intel) {
        try {
            final StorageIntel storageIntel = (StorageIntel) intel;
            return storageIntel.getStorage();
        } catch (final Exception exception) {
            log.warn("Could not extract storage from intel", exception);
        }
        return null;
    }

    @Override
    public void reportColonyAboutToBeDecivilized(final MarketAPI market, final boolean fullyDestroyed) {}

    @Override
    public void reportColonyDecivilized(final MarketAPI market, final boolean fullyDestroyed) {
        updateNeeded();
    }

    @Override
    public void reportPlayerColonizedPlanet(final PlanetAPI planet) {
        updateNeeded();
    }

    @Override
    public void reportPlayerAbandonedColony(final MarketAPI colony) {
        updateNeeded();
    }

    @Override
    public void reportPlayerOpenedMarket(final MarketAPI market) {}

    @Override
    public void reportPlayerClosedMarket(final MarketAPI market) {
        updateNeeded();
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(final MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(final PlayerMarketTransaction transaction) {}
}
