package stelnet.board.query;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.PlayerMarketTransaction;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyInteractionListener;
import com.fs.starfarer.api.campaign.listeners.ListenerUtil;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;
import stelnet.util.EconomyUtils;
import stelnet.util.SectorUtils;

@Log4j
public class MarketUpdater implements EveryFrameScript, ColonyInteractionListener {

    private MarketAPI openedMarket;

    public MarketUpdater() {
        MarketProvider.reset();
    }

    @Override
    public void advance(float amount) {
        if (!SectorUtils.isPaused()) {
            return;
        }
        MarketAPI market = pickRandomMarket();
        if (openedMarket == market) {
            log.debug("Skipping currently docked market " + market.getName());
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
        markets.addAll(EconomyUtils.getMarkets());
        return markets.pick();
    }

    protected void updateMarket(MarketAPI market) {
        log.debug("Updating " + market.getId());
        ListenerUtil.reportPlayerOpenedMarketAndCargoUpdated(market);
    }
}
