package stelnet.listener;

import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.MarketProvider;
import stelnet.util.EconomyUtils;

@Log4j
public class MarketUpdateScript implements EveryFrameScript {

    @Override
    public void advance(float amount) {
        List<MarketAPI> markets = EconomyUtils.getMarkets();
        int randomInt = (int) (Math.random() * Integer.MAX_VALUE);
        int marketSize = markets.size();
        int marketNumber = (int) Math.abs(randomInt % marketSize);
        MarketAPI market = markets.get(marketNumber);
        log.debug(String.format("Updating %s, %d/%d", market.getId(), marketNumber, marketSize));
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
