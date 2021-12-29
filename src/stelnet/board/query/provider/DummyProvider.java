package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.Filter;

@Log4j
public class DummyProvider extends QueryProvider {

    public DummyProvider(QueryFactory factory) {
        super(factory);
    }

    @Override
    public List<?> getMatching(Set<Filter> filters) {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        logUsage();
    }

    private void logUsage() {
        log.warn("DummyProvider was used!");
    }
}
