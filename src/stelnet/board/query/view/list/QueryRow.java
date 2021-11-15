package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import stelnet.board.query.Query;
import uilib.HorizontalViewContainer;
import uilib.Line;
import uilib.RenderableComponent;
import uilib.property.Size;

public class QueryRow extends RenderableComponent {

    private final Query query;

    public QueryRow(float width, Query query) {
        this.query = query;
        setSize(new Size(width, 80));
        setWithScroller(false);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        float height = getSize().getHeight() - 10;
        float controlSize = 100;
        float previewSize = 300;
        float descriptionSize = getSize().getWidth() - controlSize - previewSize;
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(
            new QueryDescription(new Size(descriptionSize, height), query),
            query.getProvider().getPreview(new Size(previewSize, height)),
            new ControlButtons(new Size(controlSize, height), query)
        );
        horizontalViewContainer.render(panel, x, y);
        new Line(getSize().getWidth()).render(panel, x, height + 2 + y);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {}
}
