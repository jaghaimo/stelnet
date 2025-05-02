package stelnet.filter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.util.MemoryHelper;

@Getter
@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public class LogicalGate extends Filter {

    private final LogicalOr anyFilter;
    private final LogicalAnd allFilter;
    private final String allMemoryKey;

    @Override
    public boolean accept(Object object) {
        return getFilter().accept(object);
    }

    @Override
    public String toString() {
        return getFilter().toString();
    }

    private Filter getFilter() {
        if (MemoryHelper.getBoolean(allMemoryKey)) {
            return allFilter;
        }
        return anyFilter;
    }
}
