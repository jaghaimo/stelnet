package stelnet.board.query.view.add;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public abstract class QueryFactory {

    @Setter
    protected SizeHelper sizeHelper = new SizeHelper();

    protected void addToFilters(Set<Filter> filters, FilteringButton buttons[], String type, boolean isEnabled) {
        if (!isEnabled) {
            return;
        }
        Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (FilteringButton button : buttons) {
            if (button.isEnabled() && button.isStateOn()) {
                selectedFilters.add(button.getFilter());
            }
        }
        filters.add(new LogicalOr(selectedFilters, type));
    }

    public abstract Set<Filter> getFilters(boolean forResults);

    public abstract RenderableComponent getPreview(Set<Filter> filters, Size size);

    public abstract QueryProvider getProvider();

    protected abstract List<Renderable> getQueryBuildingComponents();

    protected abstract Button[] getFinalComponents();
}
