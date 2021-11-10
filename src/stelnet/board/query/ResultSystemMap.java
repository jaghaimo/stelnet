package stelnet.board.query;

import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.Delegate;

public class ResultSystemMap {

    @Delegate
    private final Map<StarSystemAPI, ResultMarketMap> results = new HashMap<>();

    public void add(StarSystemAPI system, MarketAPI market, Result newResult) {
        ResultMarketMap resultList = getOrAdd(system);
        resultList.add(market, newResult);
    }

    public void addAll(StarSystemAPI system, MarketAPI market, ResultList newResultList) {
        ResultMarketMap resultList = getOrAdd(system);
        resultList.addAll(market, newResultList);
    }

    private void addIfMissing(StarSystemAPI system) {
        if (!containsKey(system)) {
            put(system, new ResultMarketMap());
        }
    }

    private ResultMarketMap getOrAdd(StarSystemAPI system) {
        addIfMissing(system);
        return get(system);
    }
}
