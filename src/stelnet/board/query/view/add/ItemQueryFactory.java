package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
import stelnet.board.query.provider.ItemProvider;
import uilib.Cargo;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ItemQueryFactory extends PreviewableQueryFactory {

    @Override
    protected RenderableComponent getPreviewContent(Size size) {
        ItemProvider itemProvider = new ItemProvider();
        CargoAPI cargo = itemProvider.getWeapons();
        cargo.addAll(itemProvider.getFighters());
        cargo.addAll(itemProvider.getModspecs());
        return new Cargo(cargo, "No matching items found.", size);
    }
}
