package stelnet.board.query.view.add;

import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ShipProvider;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.Ships;
import uilib.property.Size;

public class ShipQueryFactory extends QueryFactory {

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        return new Ships(new ShipProvider().getShips(), "No matching ships found.", size);
    }
}
