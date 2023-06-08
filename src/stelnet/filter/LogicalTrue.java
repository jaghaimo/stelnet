package stelnet.filter;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class LogicalTrue extends Filter {

    @Override
    public boolean accept(Object object) {
        return true;
    }
}
