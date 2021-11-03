package stelnet.board.query.view.add;

import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ModspecQueryFactory extends PreviewableQueryFactory {

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        return new Cargo(new CargoProvider().getModspecs(), "No matching modspecs found.", size);
    }
}
