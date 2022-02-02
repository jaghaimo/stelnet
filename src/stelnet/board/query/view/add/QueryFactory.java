package stelnet.board.query.view.add;

import java.util.List;
import java.util.Set;
import lombok.Setter;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.view.FilterAwareFactory;
import stelnet.filter.Filter;
import uilib.Button;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public abstract class QueryFactory extends FilterAwareFactory {

    @Setter
    protected SizeHelper sizeHelper = new SizeHelper();

    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return getProvider().getPreview(filters, size);
    }

    public abstract Set<Filter> getFilters();

    public abstract QueryProvider getProvider();

    protected abstract List<Renderable> getQueryBuildingComponents();

    protected abstract Button[] getFinalComponents();
}
