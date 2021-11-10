package stelnet.board.query;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;

@Getter
@Setter
@RequiredArgsConstructor
public class Query {

    private final QueryProvider provider;
    private final List<Filter> filters;
    private boolean isEnabled = false;

    public void disable() {
        setEnabled(false);
    }

    public void enable() {
        setEnabled(true);
    }

    public void toggle() {
        setEnabled(!isEnabled);
    }
}
