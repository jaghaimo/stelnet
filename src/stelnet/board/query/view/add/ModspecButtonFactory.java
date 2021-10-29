package stelnet.board.query.view.add;

import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ModspecProvider;
import uilib.Cargo;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class ModspecButtonFactory implements RenderableFactory {

    public ModspecButtonFactory() {}

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> containers = new LinkedList<>();
        containers.add(new Cargo(new ModspecProvider().getModspecs(), "No modspecs", new Size(width, 500)));
        return containers;
    }
}
