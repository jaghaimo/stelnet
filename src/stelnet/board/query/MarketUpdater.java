package stelnet.board.query;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;
import stelnet.util.EconomyUtils;

@Log4j
public class MarketUpdater implements EveryFrameScript {

    @Override
    public void advance(float amount) {
        WeightedRandomPicker<MarketAPI> markets = new WeightedRandomPicker<>(true);
        markets.addAll(EconomyUtils.getMarkets());
        MarketAPI market = markets.pick();
        log.debug("Updating " + market.getId());
        MarketProvider.updateMarket(market);
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean runWhilePaused() {
        return false;
    }
}
