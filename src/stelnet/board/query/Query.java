package stelnet.board.query;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import stelnet.util.SectorUtils;
import stelnet.util.StringUtils;
import uilib.RenderableComponent;
import uilib.RenderableShowComponent;
import uilib.property.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class Query {

    private final String createdAt = SectorUtils.getCurrentClock().getDateString();
    private final QueryManager manager;
    private final QueryProvider provider;
    private final Set<Filter> filters;
    private final String type;
    private boolean isEnabled = true;
    private int number = 0;
    private int resultNumber = 0;

    public void delete() {
        manager.deleteQuery(this);
    }

    public RenderableComponent getPreview(Size size) {
        RenderableShowComponent preview = provider.getPreview(filters, size);
        preview.setMaxElements(30);
        return preview;
    }

    public List<ResultSet> execute(GroupingStrategy groupingStrategy) {
        Set<Filter> allFilters = manager.getMarketFilters();
        allFilters.addAll(filters);
        List<ResultSet> results = provider.getResults(allFilters, groupingStrategy);
        resultNumber = 0;
        for (ResultSet resultSet : results) {
            resultNumber += resultSet.getResultCount();
        }
        return results;
    }

    public void refresh() {
        manager.updateIntel();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Query) {
            Set<Filter> objFilters = ((Query) obj).getFilters();
            return CollectionUtils.equals(filters, objFilters);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return StringUtils.join(filters, "||", L10n.get(QueryL10n.EMPTY_FILTER));
    }
}
