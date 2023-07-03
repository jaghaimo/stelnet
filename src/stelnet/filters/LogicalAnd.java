package stelnet.filters;

import java.util.Collection;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class LogicalAnd<T> implements Filter<T> {

    private final Collection<Filter<T>> filters;
    private final String type;

    public LogicalAnd(final Collection<Filter<T>> filters) {
        this(filters, "");
    }

    @Override
    public boolean accept(final T object) {
        for (final Filter<T> filter : filters) {
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

    @Override
    public String toString() {
        if (filters.isEmpty()) {
            return "";
        }
        return type + ":" + FilterHelper.name(filters, ", ", "");
    }
}
