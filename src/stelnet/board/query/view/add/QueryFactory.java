package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalNot;
import stelnet.filter.LogicalOr;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public abstract class QueryFactory {

    @Setter
    protected SizeHelper sizeHelper = new SizeHelper();

    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return getProvider().getPreview(filters, size);
    }

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

    protected Set<Filter> negateFilters(Set<Filter> filters) {
        Set<Filter> negatedFilters = new LinkedHashSet<>();
        for (Filter filter : filters) {
            negatedFilters.add(new LogicalNot(filter));
        }
        return negatedFilters;
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

    public abstract Set<Filter> getFilters(boolean forResults);

    public abstract QueryProvider getProvider();

    protected abstract List<Renderable> getQueryBuildingComponents();

    protected abstract Button[] getFinalComponents();
}
