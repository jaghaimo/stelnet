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

    protected SizeHelper sizeHelper;

    @Override
    public List<Renderable> create(Size size) {
        sizeHelper = new SizeHelper(size);
        Renderable container = getContainer();
        Size previewSize = new Size(sizeHelper.getPreviewWidth(), sizeHelper.getHeight());
        Renderable preview = getPreview(previewSize);
        return Collections.<Renderable>singletonList(new HorizontalViewContainer(container, preview));
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
        Position offset = new Position(0, -20);
        Heading heading = new Heading("Preview");
        heading.setSize(new Size(size.getWidth(), 25));
        heading.setOffset(offset);
        previewContent.setSize(size.reduce(new Size(0, 25)));
        previewContent.setOffset(offset);
        VerticalViewContainer verticalView = new VerticalViewContainer(heading, previewContent);
        verticalView.setSize(size.reduce(new Size(0, 25)));
        return verticalView;
    }

    protected Renderable getPreview(Size size) {
        return getPreview(getUnimplemented("getPreview(Size size)"), size);
    }

    private AbstractRenderable getUnimplemented(String method) {
        return new Paragraph("Please override " + method + " method.", sizeHelper.getGroupAndTextWidth());
    }
}
