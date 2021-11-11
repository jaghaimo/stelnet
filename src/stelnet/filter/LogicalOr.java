package stelnet.filter;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequiredArgsConstructor
public class LogicalOr extends Filter {

    private final Collection<Filter> filters;

    @Override
    public boolean accept(Object object) {
        for (Filter filter : filters) {
            if (filter == null) {
                log.warn("Skipping null filter!");
                continue;
            }
            if (filter.accept(object)) {
                return true;
            }
        }
        return false;
    }
}
