package stelnet.filter;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class LogicalFalse extends Filter {

    @Override
    public boolean accept(Object object) {
        return false;
    }
}
