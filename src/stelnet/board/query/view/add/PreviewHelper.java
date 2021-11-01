package stelnet.board.query.view.add;

import java.util.List;
import uilib.AbstractRenderable;
import uilib.Heading;
import uilib.Renderable;
import uilib.VerticalViewContainer;
import uilib.property.Position;
import uilib.property.Size;

public class PreviewHelper {

    public void addPreview(List<Renderable> containers, AbstractRenderable previewContent, Size size) {
        containers.add(getPreview(previewContent, size));
    }

    public Renderable getPreview(AbstractRenderable previewContent, Size size) {
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
}
