package stelnet.board.query.view.add;

import java.util.Collections;
import java.util.List;
import stelnet.util.L10n;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Line;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
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
        Renderable padding = new Spacer(12);
        Renderable preview = getPreview(getPreviewContent(previewSize), previewSize);
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(container, padding, preview);
        return Collections.<Renderable>singletonList(horizontalViewContainer);
    }

    protected void addPreview(List<Renderable> elements, RenderableComponent previewContent, Size size) {
        elements.add(getPreview(previewContent, size));
    }

    protected void beginSection(List<Renderable> elements, Enum<?> translationId) {
        float width = sizeHelper.getGroupAndTextWidth();
        elements.add(new Spacer(10));
        elements.add(new Paragraph(L10n.get(translationId), width));
        Line line = new Line(width);
        line.setOffset(new Position(0, -6));
        elements.add(line);
        elements.add(new Spacer(2));
    }

    protected void endSection(List<Renderable> elements) {
        Line line = new Line(sizeHelper.getGroupAndTextWidth());
        // line.setOffset(new Position(0, -2));
        elements.add(line);
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
