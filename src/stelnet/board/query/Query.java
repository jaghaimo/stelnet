package stelnet.board.query;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;

@RequiredArgsConstructor
public class Query {

    private final QueryProvider provider;
    private final Set<Filter> filters;

    @Getter
    @Setter
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
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
