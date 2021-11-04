package stelnet.filter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public class LogicalAndFilter extends Filter {

    private final List<Filter> filters;

    @Override
    public boolean accept(Object object) {
        for (Filter filter : filters) {
            if (filter == null) {
                log.warn("Skipping null filter!");
                continue;
            }
            if (!filter.accept(object)) {
                return false;
            }
        }
        return true;
    }
}