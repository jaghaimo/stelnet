package stelnet.delegate;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import lombok.Getter;
import lombok.experimental.Delegate;

/**
 * Serializable, version agnostic, drop in replacement for MarketAPI.
 */
@Getter
public class MarketApiDelegate implements MarketAPI {

    private final String marketApiId;

    @Delegate
    private final transient MarketAPI marketApi;

    public MarketApiDelegate(String id) {
        marketApiId = id;
        MarketAPI market = Global.getSector().getEconomy().getMarket(id);
        marketApi = market == null ? new MarketApiDummy() : market;
    }

    public MarketApiDelegate(MarketAPI market) {
        marketApiId = market.getId();
        marketApi = market;
    }

    // TODO : Investigate why @Delegate can't work with this
    public MarketAPI clone() {
        return marketApi.clone();
    }

    public boolean isValid() {
        return marketApiId == marketApi.getId();
    }

    public Object readResolve() {
        return new MarketApiDelegate(marketApiId);
    }
}
