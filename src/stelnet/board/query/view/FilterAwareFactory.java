package stelnet.board.query.view;

import java.util.LinkedHashSet;
import java.util.Set;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;

public abstract class FilterAwareFactory {

    protected void addSelectedOrAll(Set<Filter> filters, FilteringButton buttons[], String type) {
        Set<Filter> selectedFilters = getFilters(buttons, true);
        if (selectedFilters.isEmpty()) {
            selectedFilters = getFilters(buttons, false);
        }
        filters.add(new LogicalOr(selectedFilters, type));
    }

    protected void addSelectedOrNone(Set<Filter> filters, FilteringButton buttons[], String type, boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        Set<Filter> selectedFilters = getFilters(buttons, true);
        if (!selectedFilters.isEmpty()) {
            filters.add(new LogicalOr(selectedFilters, type));
        }
    }

    protected Set<Filter> getFilters(FilteringButton buttons[], boolean wantedSelectedState) {
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (FilteringButton button : buttons) {
            boolean isSelected = button.isEnabled() && button.isStateOn();
            if (isSelected == wantedSelectedState) {
                selectedFilters.add(button.getFilter());
            }
        }
        return selectedFilters;
    }

    protected Set<Filter> getNegatedFilters(FilteringButton buttons[], boolean wantedSelectedState) {
        Set<Filter> selectedFilters = getFilters(buttons, wantedSelectedState);
        return negateFilters(selectedFilters);
    }

    private Set<Filter> negateFilters(Set<Filter> filters) {
        Set<Filter> negatedFilters = new LinkedHashSet<>();
        for (Filter filter : filters) {
            negatedFilters.add(new LogicalNot(filter));
        }
        return negatedFilters;
    }
}
