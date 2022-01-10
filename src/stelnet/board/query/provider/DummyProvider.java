package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import uilib.RenderableComponent;
import uilib.Spacer;
import uilib.property.Size;

@Log4j
public class DummyProvider extends QueryProvider {

    @Override
    public List<?> getMatching(Set<Filter> filters) {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        logUsage();
        return new Spacer(0);
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
