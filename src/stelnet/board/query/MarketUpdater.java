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
    private MarketAPI openedMarket;

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

    @Override
    public void advance(float amount) {
        if (!Global.getSector().isPaused()) {
            return;
        }
        MarketAPI market = pickRandomMarket();
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
    public void reportPlayerOpenedMarket(MarketAPI market) {
        if (openedMarket == null) {
            openedMarket = market;
        }
    }

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {
        openedMarket = null;
    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {}

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {}

    protected MarketAPI pickRandomMarket() {
        WeightedRandomPicker<MarketAPI> markets = new WeightedRandomPicker<>(true);
        markets.addAll(Global.getSector().getEconomy().getMarketsCopy());
        return markets.pick();
    }

    protected void updateMarket(MarketAPI market) {
        log.debug("Updating " + market.getId());
        MarketProvider.updateMarket(market);
    }
}
