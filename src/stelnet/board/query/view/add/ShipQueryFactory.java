package stelnet.board.query.view.add;

import stelnet.board.query.provider.ShipProvider;
import uilib.RenderableComponent;
import uilib.Ships;
import uilib.property.Size;

public class ShipQueryFactory extends PreviewableQueryFactory {

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        return new Ships(new ShipProvider().getShips(), "No matching ships found.", size);
    }
}
