package stelnet.filter;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogicalOrFilter extends Filter {

    private final List<Filter> filters;

    @Override
    public boolean accept(Object object) {
        for (Filter filter : filters) {
            if (filter.accept(object)) {
                return true;
            }
        }
        return false;
    }
}
