package stelnet.board.query;

import com.fs.starfarer.api.Global;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import uilib.RenderableComponent;
import uilib.RenderableShowComponent;
import uilib.property.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class Query {

    private final String createdAt = Global.getSector().getClock().getDateString();
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
        preview.setMaxElements(25);
        return preview;
    }

    public List<ResultSet> execute(GroupingStrategy groupingStrategy) {
        List<ResultSet> results = provider.getResults(filters, groupingStrategy);
        CollectionUtils.reduce(results, getResultFilters());
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
        return CollectionUtils.join(filters, "||", L10n.get(QueryL10n.EMPTY_FILTER));
    }

    private Set<Filter> getResultFilters() {
        Set<Filter> resultFilters = new LinkedHashSet<>();
        resultFilters.add(new LogicalOr(manager.getSubmarketFilters(), "submarkets"));
        resultFilters.addAll(manager.getOtherFilters());
        addDmodCountFilter(resultFilters);
        resultFilters.addAll(manager.getDModTypesFilters());
        return resultFilters;
    }

    private void addDmodCountFilter(Set<Filter> resultFilters) {
        Set<Filter> dModCountFilters = manager.getDModCountFilters();
        if (dModCountFilters.isEmpty()) {
            return;
        }
        resultFilters.add(new LogicalOr(dModCountFilters, "dmod count"));
    }
}
