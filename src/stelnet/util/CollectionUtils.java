package stelnet.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

    public static String join(Iterable<?> iterable, String delimiter, String empty) {
        int delimiterLength = delimiter.length();
        StringBuilder builder = new StringBuilder();
        Iterator<?> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            String nextString = iterator.next().toString().trim();
            if (nextString.isEmpty()) {
                continue;
            }
            builder.append(delimiter);
            builder.append(nextString);
        }
        if (builder.length() > delimiterLength) {
            return builder.substring(delimiterLength);
        }
        return empty;
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] merge(T[] array1, T[]... arrays) {
        List<T> list = new LinkedList<>();
        Collections.addAll(list, array1);
        for (T[] array : arrays) {
            Collections.addAll(list, array);
        }
        return list.toArray(array1);
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
