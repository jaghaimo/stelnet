package stelnet.board.query.view.add;

import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ShipProvider;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Ships;
import uilib.property.Size;

public class ShipButtonFactory implements RenderableFactory {

    public ShipButtonFactory() {}

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> containers = new LinkedList<>();
        containers.add(new Ships(new ShipProvider().getShips(), "No ships", new Size(width, 500)));
        return containers;
    }
}
