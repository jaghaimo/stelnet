package stelnet.board.query;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;
import stelnet.util.EconomyUtils;
import stelnet.util.SectorUtils;

@Log4j
public class MarketUpdater implements EveryFrameScript {

    public MarketUpdater() {
        log.info("Adding transient market updater");
        MarketProvider.reset();
    }

    @Override
    public void advance(float amount) {
        if (!SectorUtils.isPaused()) {
            return;
        }
        updateMarket(pickRandomMarket());
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }

    protected MarketAPI pickRandomMarket() {
        WeightedRandomPicker<MarketAPI> markets = new WeightedRandomPicker<>(true);
        markets.addAll(EconomyUtils.getMarkets());
        return markets.pick();
    }

    protected void updateMarket(MarketAPI market) {
        log.info("Updating " + market.getId());
        MarketProvider.updateMarket(market);
    }
}
