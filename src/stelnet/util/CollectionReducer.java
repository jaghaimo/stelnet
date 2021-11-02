package stelnet.util;

import java.util.Iterator;
import stelnet.filter.Filter;

public class CollectionReducer {

    public static <E, F extends Filter<E>> void reduce(Iterable<E> entities, Iterable<F> filters) {
        Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!matches(entity.next(), filters)) {
                entity.remove();
            }
        }
    }

    public static <E, F extends Filter<E>> void reduce(Iterable<E> entities, F filter) {
        Iterator<E> entity = entities.iterator();
        while (entity.hasNext()) {
            if (!filter.accept(entity.next())) {
                entity.remove();
            }
        }
    }

    private static <E, F extends Filter<E>> boolean matches(E item, Iterable<F> filters) {
        for (F filter : filters) {
            if (!filter.accept(item)) {
                return false;
            }
        }
        return true;
    }
}
