package stelnet.board.query.view.add;

import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.Renderable;
import uilib.property.Size;

public class ModspecQueryFactory extends QueryTypeFactory {

    @Override
    protected Renderable getPreview(Size size) {
        Cargo cargo = new Cargo(new CargoProvider().getModspecs(), "No matching modspecs found.", size);
        return getPreview(cargo, size);
    }
}
