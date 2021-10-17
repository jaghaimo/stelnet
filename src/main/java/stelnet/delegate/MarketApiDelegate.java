package stelnet.delegate;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import lombok.Getter;
import lombok.experimental.Delegate;
import stelnet.util.Economy;

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
        MarketAPI market = Economy.getMarket(id);
        marketApi = market == null ? new MarketApiDummy() : market;
    }

    public MarketApiDelegate(MarketAPI market) {
        marketApiId = market.getId();
        marketApi = market;
    }

    // BUG : @Delegate ignores methods belonging to Object
    public MarketAPI clone() {
        return marketApi.clone();
    }

    public boolean isValid() {
        return marketApiId.equals(marketApi.getId());
    }

    public Object readResolve() {
        return new MarketApiDelegate(marketApiId);
    }
}
