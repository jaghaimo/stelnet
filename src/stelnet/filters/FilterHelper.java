package stelnet.filters;

import java.util.Iterator;

public class FilterHelper {

    public static String name(final Iterable<?> iterable, final String delimiter, final String empty) {
        final int delimiterLength = delimiter.length();
        final StringBuilder builder = new StringBuilder();
        for (final Object o : iterable) {
            final String nextString = o.toString().trim();
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

    public static <E, F extends Filter<E>> void reduce(final Iterable<E> entities, final Iterable<F> filters) {
        final Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!matches(entity.next(), filters)) {
                entity.remove();
            }
        }
    }

    public static <E, F extends Filter<E>> void reduce(final Iterable<E> entities, final F filter) {
        final Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!filter.accept(entity.next())) {
                entity.remove();
            }
        }
    }

    private static <E, F extends Filter<E>> boolean matches(final E item, final Iterable<F> filters) {
        for (final F filter : filters) {
            if (!filter.accept(item)) {
                return false;
            }
        }
        return true;
    }
}
