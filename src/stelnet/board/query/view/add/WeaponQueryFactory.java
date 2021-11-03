package stelnet.board.query.view.add;

import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.RenderableComponent;
import uilib.property.Size;

public class WeaponQueryFactory extends PreviewableQueryFactory {

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        return new Cargo(new CargoProvider().getWeapons(), "No matching weapons found.", size);
    }
}
