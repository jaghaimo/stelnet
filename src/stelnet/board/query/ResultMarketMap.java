package stelnet.board.query;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.HashMap;
import java.util.Map;
import lombok.experimental.Delegate;

public class ResultMarketMap {

    @Delegate
    private final Map<MarketAPI, ResultList> results = new HashMap<>();

    public void add(MarketAPI market, Result newResult) {
        ResultList resultList = getOrAdd(market);
        resultList.add(newResult);
    }

    public void addAll(MarketAPI market, ResultList newResultList) {
        ResultList resultList = getOrAdd(market);
        resultList.addAll(newResultList.getResultList());
    }

    public String getResultNumber() {
        int size = 0;
        for (ResultList resultList : results.values()) {
            size += resultList.size();
        }
        return String.valueOf(size);
    }

    public String getMarketNumber() {
        return String.valueOf(size());
    }

    private void addIfMissing(MarketAPI market) {
        if (!containsKey(market)) {
            put(market, new ResultList());
        }
    }

    private ResultList getOrAdd(MarketAPI market) {
        addIfMissing(market);
        return get(market);
    }
}
