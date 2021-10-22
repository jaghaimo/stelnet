package stelnet.filter.market;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import lombok.extern.log4j.Log4j;

@Log4j
public class IsNotHidden implements MarketFilter {

    public boolean accept(MarketAPI market) {
        log.debug(String.format("Considering %s (%b)", market.getName(), !market.isHidden()));
        return !market.isHidden();
    }
}
