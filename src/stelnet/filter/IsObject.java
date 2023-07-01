package stelnet.filter;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class IsObject extends Filter {

    private final Object object;

    @Override
    public boolean accept(final Object object) {
        return this.object == object;
    }
}
