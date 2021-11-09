package stelnet.filter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogicalNot extends Filter {

    private final Filter filter;

    @Override
    public boolean accept(Object object) {
        return !filter.accept(object);
    }
}