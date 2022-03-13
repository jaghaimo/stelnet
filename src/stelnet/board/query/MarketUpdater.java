package stelnet.board.query;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;
import stelnet.util.StelnetHelper;

@Log4j
public class MarketUpdater implements EveryFrameScript, ColonyInteractionListener {

    private static transient MarketUpdater instance;
    private MarketAPI openedMarket;

    public static MarketUpdater getInstance() {
        if (instance == null) {
            instance = new MarketUpdater();
        }
        Global.getSector().addTransientScript(instance);
        Global.getSector().getListenerManager().addListener(instance, true);
        MarketProvider.reset();
        return instance;
    }

    public static void reset() {
        if (instance != null) {
            log.debug("Forcing fake player closed market");
            instance.reportPlayerClosedMarket(null);
        }
    }

    private MarketUpdater() {}

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
        markets.addAll(StelnetHelper.getMarkets());
        return markets.pick();
    }

    protected void updateMarket(MarketAPI market) {
        log.debug("Updating " + market.getId());
        MarketProvider.updateMarket(market);
    }
}
