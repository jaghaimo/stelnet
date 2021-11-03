package stelnet.board.query.view.add;

import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.Renderable;
import uilib.property.Size;

public class FighterQueryFactory extends QueryTypeFactory {

    @Override
    protected Renderable getPreview(Size size) {
        Cargo cargo = new Cargo(new CargoProvider().getFighters(), "No matching fighters found.", size);
        return getPreview(cargo, size);
    }
}
