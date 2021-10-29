package stelnet.filter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NegateFilter extends Filter {

    private final Filter filter;

    public boolean accept(Object object) {
        return !filter.accept(object);
    }
}
