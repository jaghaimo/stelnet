package stelnet.board.query.view.list;

import java.util.LinkedList;
import java.util.List;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class QueryListFactory implements RenderableFactory {

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.add(new Paragraph("Not implemented yet!", size.getWidth()));
        renderables.add(new DeleteAllButton());
        return renderables;
    }
}
