package stelnet.filters;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class Not<T> implements Filter<T> {

    private final Filter<T> filter;

    @Override
    public boolean accept(final T object) {
        return !filter.accept(object);
    }

    @Override
    public String toString() {
        return filter.toString();
    }
}
