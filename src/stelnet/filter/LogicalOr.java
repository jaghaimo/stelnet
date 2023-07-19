package stelnet.filter;

import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.util.CollectionUtils;

@Log4j
@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class LogicalOr extends Filter {

    private final Collection<Filter> filters;
    private final String type;

    @Override
    public boolean accept(final Object object) {
        for (final Filter filter : filters) {
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

    @Override
    public String toString() {
        if (filters.isEmpty()) {
            return "";
        }
        return type + ":" + CollectionUtils.join(filters, ", ", "");
    }
}
