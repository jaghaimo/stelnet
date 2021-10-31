package stelnet.board.query.view.add;

import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.ShipProvider;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Ships;
import uilib.property.Size;

public class ShipQueryFactory implements RenderableFactory {

    private PreviewHelper previewHelper = new PreviewHelper();

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        float textWidth = Math.max(width / 4, 200);
        // float groupWidth = width - 2 * textWidth;
        List<Renderable> containers = new LinkedList<>();
        addPreview(containers, new Size(textWidth, size.getHeight()));
        return containers;
    }

    private void addPreview(List<Renderable> containers, Size size) {
        Ships cargo = new Ships(new ShipProvider().getShips(), "No matching ships found.", size);
        previewHelper.addPreview(containers, cargo, size);
    }
}
