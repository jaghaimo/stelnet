package stelnet.board.query.view.add;

import java.util.Collections;
import java.util.List;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.VerticalViewContainer;
import uilib.property.Position;
import uilib.property.Size;

public abstract class PreviewableQueryFactory implements RenderableFactory {

    protected static float FIRST_ROW_HEIGHT = 24;

    protected SizeHelper sizeHelper = new SizeHelper();

    @Override
    public List<Renderable> create(Size size) {
        initSizeHelper(size);
        Renderable container = getContainer();
        Size previewSize = new Size(sizeHelper.getPreviewWidth(), sizeHelper.getHeight() - FIRST_ROW_HEIGHT);
        Renderable preview = getPreview(getPreviewContent(previewSize), previewSize);
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(container, preview);
        return Collections.<Renderable>singletonList(horizontalViewContainer);
    }

    protected void addPreview(List<Renderable> containers, RenderableComponent previewContent, Size size) {
        containers.add(getPreview(previewContent, size));
    }

    protected Renderable getContainer() {
        return new HorizontalViewContainer(
            new Paragraph("", sizeHelper.getTextWidth(), 10),
            getUnimplemented("getContainer()")
        );
    }

    protected Renderable getPreview(RenderableComponent previewContent, Size size) {
        final float MOVE_UP = 20;
        final float HEADING_HEIGHT = 25;
        Heading heading = new Heading("Preview");
        heading.setSize(new Size(size.getWidth(), HEADING_HEIGHT));
        heading.setOffset(new Position(0, -MOVE_UP));
        previewContent.setSize(size.reduce(new Size(0, HEADING_HEIGHT)));
        VerticalViewContainer verticalView = new VerticalViewContainer(heading, previewContent);
        verticalView.setSize(size.reduce(new Size(0, MOVE_UP + HEADING_HEIGHT)));
        return verticalView;
    }

    protected RenderableComponent getPreviewContent(Size size) {
        return getUnimplemented("getPreview(Size size)");
    }

    protected void initSizeHelper(Size size) {
        sizeHelper = new SizeHelper(size);
    }

    private RenderableComponent getUnimplemented(String method) {
        return new Paragraph("Please override " + method + " method.", sizeHelper.getGroupWidth(), 10);
    }
}
