package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.DummyProvider;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

@Log4j
public class DummyFactory extends QueryFactory {

    @Override
    public Set<Filter> getFilters() {
        logUsage();
        return Collections.emptySet();
    }

    @Override
    public RenderableComponent getPreview(Size size) {
        logUsage();
        return new RenderableComponent() {
            @Override
            public void render(TooltipMakerAPI tooltip) {}
        };
    }

    @Override
    public QueryProvider getProvider() {
        logUsage();
        return new DummyProvider(this);
    }

    @Override
    protected List<Renderable> getFinalComponents() {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        logUsage();
        return Collections.emptyList();
    }

    private void logUsage() {
        log.warn("DummyFactory was used!");
    }
}
