package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Setter;
import stelnet.board.query.Query;
import uilib.HorizontalViewContainer;
import uilib.Line;
import uilib.RenderableComponent;

@Setter
public class QueryRow extends RenderableComponent {

    private final Query query;
    private boolean hasSeparator = true;
    private final HorizontalViewContainer container;
    private final float width;

    public QueryRow(float width, Query query, boolean isLeft) {
        this.query = query;
        this.width = width;
        this.container = createContainer(isLeft);
        setSize(container.getSize());
        setWithScroller(false);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        float height = container.getSize().getHeight();
        container.render(panel, x, y);
        if (hasSeparator) {
            new Line(getSize().getWidth()).render(panel, x, y);
            new Line(getSize().getWidth()).render(panel, x, height + y);
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {}

    private HorizontalViewContainer createContainer(boolean isLeft) {
        float controlSize = getControlWidth(width);
        float descriptionSize = width - controlSize;
        if (isLeft) {
            return new HorizontalViewContainer(
                new ControlButtons(controlSize, query),
                new QueryDescription(descriptionSize, query, isLeft)
            );
        }
        return new HorizontalViewContainer(
            new QueryDescription(descriptionSize, query, isLeft),
            new ControlButtons(controlSize, query)
        );
    }

    private float getControlWidth(float maxWidth) {
        return Math.min(100, maxWidth - 310);
    }
}
