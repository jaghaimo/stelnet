package stelnet.board.query;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

@Getter
@Setter
@RequiredArgsConstructor
public class Query {

    private final QueryProvider provider;
    private final Set<Filter> filters;
    private boolean isEnabled = false;

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        setEnabled(true);
    }

    public List<ResultSet> execute() {
        return provider.getResults(filters);
    }

    public void toggle() {
        setEnabled(!isEnabled);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Query) {
            Set<Filter> objFilters = ((Query) obj).getFilters();
            boolean result = CollectionUtils.equals(filters, objFilters);
            return result;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
