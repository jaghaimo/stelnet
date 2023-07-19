package stelnet.board.query;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;

@Log4j
public class MarketUpdater implements EveryFrameScript, ColonyInteractionListener {

    private static transient MarketUpdater instance;

    public static void register() {
        if (instance == null) {
            instance = new MarketUpdater();
        }
        Global.getSector().addTransientScript(instance);
        Global.getSector().getListenerManager().addListener(instance, true);
        MarketProvider.reset();
        log.debug("Enabled Market Updater script");
    }

    public static void unregister() {
        if (instance != null) {
            Global.getSector().removeTransientScript(instance);
            Global.getSector().getListenerManager().removeListener(instance);
            instance = null;
            log.debug("Disabled Market Updater script");
        }
    }

    private MarketAPI openedMarket;

    @Override
    public void advance(final float amount) {
        if (!Global.getSector().isPaused()) {
            return;
        }
        final MarketAPI market = pickRandomMarket();
        if (openedMarket == market) {
            log.debug("Skipping currently opened market " + market.getId());
            return;
        }
        updateMarket(market);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return true;
    }

    @Override
    public void reportPlayerOpenedMarket(final MarketAPI market) {
        if (openedMarket == null) {
            openedMarket = market;
        }
    }

    @Override
    public void reportPlayerClosedMarket(final MarketAPI market) {
        openedMarket = null;
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(final MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(final PlayerMarketTransaction transaction) {}

    protected MarketAPI pickRandomMarket() {
        final WeightedRandomPicker<MarketAPI> markets = new WeightedRandomPicker<>(true);
        markets.addAll(Global.getSector().getEconomy().getMarketsCopy());
        return markets.pick();
    }

    protected void updateMarket(final MarketAPI market) {
        log.debug("Updating " + market.getId());
        MarketProvider.updateMarket(market);
    }
}
