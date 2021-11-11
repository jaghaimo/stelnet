package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.ResultSet;
import stelnet.filter.Filter;

@Log4j
public class DummyProvider extends QueryProvider {

    @Override
    public List<?> getMatching(List<Filter> filters) {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    protected void processMarkets(List<ResultSet> resultSets, List<MarketAPI> markets, List<Filter> filters) {
        logUsage();
    }

    private void logUsage() {
        log.warn("DummyProvider was used!");
    }
}
