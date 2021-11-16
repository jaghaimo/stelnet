package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Setter;
import stelnet.board.query.Query;
import uilib.Line;
import uilib.RenderableComponent;
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
            new QueryDescription(width, query),
            new Line(width),
            new ControlButtons(width, query)
        );
    }
}
