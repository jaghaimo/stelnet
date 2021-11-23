package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Setter;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;

@Setter
public class QueryRow extends RenderableComponent {

    private final Query query;
    private final VerticalViewContainer container;
    private final float width;

    public QueryRow(float width, Query query) {
        this.query = query;
        this.width = width;
        this.container = createContainer();
        setSize(container.getSize());
        setWithScroller(false);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        container.render(panel, x, y);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {}

    private VerticalViewContainer createContainer() {
        return new VerticalViewContainer(
            new QueryInfo(width, query),
            new QueryControls(width, query),
            new QueryDescription(width, query),
            new Spacer(UiConstants.DEFAULT_SPACER)
        );
    }
}
