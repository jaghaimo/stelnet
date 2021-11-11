package stelnet.util;

import java.util.Collection;
import java.util.Iterator;
import stelnet.filter.Filter;

public class CollectionUtils {

    public static boolean equals(Collection<Filter> firstFilters, Collection<Filter> secondFilters) {
        if (firstFilters.size() != secondFilters.size()) {
            return false;
        }
        for (Filter filter : firstFilters) {
            if (!secondFilters.contains(filter)) {
                return false;
            }
        }
        return true;
    }

    public static <E, F extends Filter> void reduce(Iterable<E> entities, Iterable<F> filters) {
        Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!matches(entity.next(), filters)) {
                entity.remove();
            }
        }
    }

    public static <E, F extends Filter> void reduce(Iterable<E> entities, F filter) {
        Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!filter.accept(entity.next())) {
                entity.remove();
            }
        }
    }

    private static <E, F extends Filter> boolean matches(E item, Iterable<F> filters) {
        for (F filter : filters) {
            if (!filter.accept(item)) {
                return false;
            }
        }
        return true;
    }
}
