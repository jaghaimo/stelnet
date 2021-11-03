package stelnet.board.query.view.add;

import java.util.Collections;
import java.util.List;
import uilib.AbstractRenderable;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Position;
import uilib.property.Size;

public abstract class QueryTypeFactory implements RenderableFactory {

    protected static final float FIRST_ROW_HEIGHT = 24;

    protected SizeHelper sizeHelper;

    @Override
    public List<Renderable> create(Size size) {
        sizeHelper = new SizeHelper(size);
        Renderable container = getContainer();
        Size previewSize = new Size(sizeHelper.getPreviewWidth(), sizeHelper.getHeight() - FIRST_ROW_HEIGHT);
        Renderable preview = getPreview(previewSize);
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(container, preview);
        // horizontalViewContainer.setSize(horizontalViewContainer.getSize().reduce(new Size(0, 24)));
        return Collections.<Renderable>singletonList(horizontalViewContainer);
    }

    protected void addPreview(List<Renderable> containers, AbstractRenderable previewContent, Size size) {
        containers.add(getPreview(previewContent, size));
    }

    protected Renderable getContainer() {
        VerticalViewContainer container = new VerticalViewContainer(getUnimplemented("getContainer()"));
        container.setSize(new Size(sizeHelper.getGroupAndTextWidth(), 25));
        return container;
    }

    protected Renderable getPreview(AbstractRenderable previewContent, Size size) {
        final float MOVE_UP = 20;
        final float HEADING_HEIGHT = 25;
        Position offset = new Position(0, -MOVE_UP);
        Heading heading = new Heading("Preview");
        heading.setSize(new Size(size.getWidth(), HEADING_HEIGHT));
        heading.setOffset(offset);
        previewContent.setSize(size.reduce(new Size(0, MOVE_UP + HEADING_HEIGHT)));
        previewContent.setOffset(offset);
        VerticalViewContainer verticalView = new VerticalViewContainer(heading, previewContent);
        verticalView.setSize(size.reduce(new Size(0, MOVE_UP + HEADING_HEIGHT)));
        return verticalView;
    }

    protected Renderable getPreview(Size size) {
        return getPreview(getUnimplemented("getPreview(Size size)"), size);
    }

    private AbstractRenderable getUnimplemented(String method) {
        return new Paragraph("Please override " + method + " method.", sizeHelper.getGroupAndTextWidth());
    }
}
