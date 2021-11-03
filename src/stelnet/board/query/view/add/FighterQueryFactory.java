package stelnet.board.query.view.add;

import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.provider.CargoProvider;
import uilib.Cargo;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class FighterQueryFactory implements RenderableFactory {

    private SizeHelper sizeHelper;
    private final PreviewHelper previewHelper = new PreviewHelper();

    @Override
    public List<Renderable> create(Size size) {
        sizeHelper = new SizeHelper(size);
        List<Renderable> containers = new LinkedList<>();
        addPreview(containers, new Size(sizeHelper.getTextWidth(), size.getHeight()));
        return containers;
    }

    private void addPreview(List<Renderable> containers, Size size) {
        Cargo cargo = new Cargo(new CargoProvider().getFighters(), "No matching fighters found.", size);
        previewHelper.addPreview(containers, cargo, size);
    }
}
