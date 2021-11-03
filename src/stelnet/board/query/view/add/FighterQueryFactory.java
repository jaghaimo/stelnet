package stelnet.board.query.view.add;

import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.RenderableComponent;
import uilib.property.Size;

public class FighterQueryFactory extends PreviewableQueryFactory {

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        return new Cargo(new CargoProvider().getFighters(), "No matching fighters found.", size);
    }
}
