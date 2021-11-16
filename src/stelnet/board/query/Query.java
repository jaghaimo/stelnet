package stelnet.board.query;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import uilib.RenderableComponent;
import uilib.property.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class Query {

    private final QueryManager manager;
    private final QueryProvider provider;
    private final Set<Filter> filters;
    private boolean isEnabled = true;
    private boolean isSelected = false;
    private int resultNumber = 0;

    public void disable() {
        setEnabled(false);
    }

    public void delete() {
        manager.deleteQuery(this);
    }

    public RenderableComponent getPreview(Size size) {
        return provider.getPreview(size);
    }

    public void enable() {
        setEnabled(true);
    }

    public List<ResultSet> execute() {
        List<ResultSet> results = provider.getResults(filters);
        resultNumber = results.size();
        return results;
    }

    public void select() {
        manager.selectQuery(this);
    }

    public void toggle() {
        setEnabled(!isEnabled);
        manager.updateIntel();
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

    @Override
    public String toString() {
        return StringUtils.join(filters, "||", L10n.get(QueryL10n.EMPTY_FILTER));
    }
}
