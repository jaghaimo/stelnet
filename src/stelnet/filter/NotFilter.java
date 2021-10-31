package stelnet.filter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFilter extends Filter {

    private final Filter filter;

    @Override
    protected boolean acceptImpl(Object object) {
        return !filter.acceptImpl(object);
    }
}
