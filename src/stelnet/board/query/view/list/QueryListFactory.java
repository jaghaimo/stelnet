package stelnet.board.query.view.list;

import java.util.Collections;
import java.util.List;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class QueryListFactory implements RenderableFactory {

    @Override
    public List<Renderable> create(Size size) {
        return Collections.<Renderable>singletonList(new Paragraph("No queries to be shown.", size.getWidth()));
    }
}
