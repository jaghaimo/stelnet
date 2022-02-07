package stelnet.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class LogicalNot extends Filter {

    private final Filter filter;

    @Override
    public boolean accept(Object object) {
        return !filter.accept(object);
    }

    @Override
    public String toString() {
        return filter.toString();
    }
}
