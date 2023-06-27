package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.QueryManager;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import uilib.RenderableShowComponent;
import uilib.property.Size;

@Log4j
public class DummyQueryProvider extends QueryProvider {

    @Override
    public Set<Filter> getAdditionalFilters(final QueryManager manager) {
        return Collections.emptySet();
    }

    @Override
    public List<?> getMatching(final Set<Filter> filters) {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    public RenderableShowComponent getPreview(final Set<Filter> filters, final Size size) {
        logUsage();
        return new RenderableShowComponent(0) {
            @Override
            public void render(final TooltipMakerAPI tooltip) {
                logUsage();
            }
        };
    }

    @Override
    protected void processMarkets(
        final List<ResultSet> resultSets,
        final List<MarketAPI> markets,
        final Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        logUsage();
    }

    private void logUsage() {
        log.warn("DummyProvider was used!");
    }
}
