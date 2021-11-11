package stelnet.board.query.view.add;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Collections;
import java.util.List;
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
    protected List<Filter> getFilters() {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        logUsage();
        return Collections.emptyList();
    }

    @Override
    protected QueryProvider getProvider() {
        logUsage();
        return new DummyProvider();
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        logUsage();
        return new RenderableComponent() {
            @Override
            public void render(TooltipMakerAPI tooltip) {}
        };
    }

    private void logUsage() {
        log.warn("DummyFactory was used!");
    }
}
