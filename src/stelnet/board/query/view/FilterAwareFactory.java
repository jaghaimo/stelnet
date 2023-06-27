package stelnet.board.query.view;

import java.util.LinkedHashSet;
import java.util.Set;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;

public abstract class FilterAwareFactory {

    protected void addSelectedOrAll(final Set<Filter> filters, final FilteringButton buttons[], final String type) {
        Set<Filter> selectedFilters = getFilters(buttons, true);
        if (selectedFilters.isEmpty()) {
            selectedFilters = getFilters(buttons, false);
        }
        filters.add(new LogicalOr(selectedFilters, type));
    }

    protected void addSelectedOrNone(
        final Set<Filter> filters,
        final FilteringButton buttons[],
        final String type,
        final boolean isEnabled
    ) {
        if (!isEnabled) {
            return;
        }
        final Set<Filter> selectedFilters = getFilters(buttons, true);
        if (!selectedFilters.isEmpty()) {
            filters.add(new LogicalOr(selectedFilters, type));
        }
    }

    protected Set<Filter> getFilters(final FilteringButton buttons[], final boolean wantedSelectedState) {
        final Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (final FilteringButton button : buttons) {
            final boolean isSelected = button.isEnabled() && button.isStateOn();
            if (isSelected == wantedSelectedState) {
                selectedFilters.add(button.getFilter());
            }
        }
        return selectedFilters;
    }
}
