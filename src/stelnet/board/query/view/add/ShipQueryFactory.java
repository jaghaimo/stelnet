package stelnet.board.query.view.add;

import stelnet.board.query.provider.ShipProvider;
import uilib.Renderable;
import uilib.Ships;
import uilib.property.Size;

public class ShipQueryFactory extends QueryTypeFactory {

    @Override
    protected Renderable getPreview(Size size) {
        Ships cargo = new Ships(new ShipProvider().getShips(), "No matching ships found.", size);
        return getPreview(cargo, size);
    }
}
