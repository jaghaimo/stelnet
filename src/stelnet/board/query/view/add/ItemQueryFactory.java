package stelnet.board.query.view.add;

import com.fs.starfarer.api.campaign.CargoAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.provider.ItemProvider;
import uilib.Cargo;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ItemQueryFactory extends QueryFactory {

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        beginSection(elements, CommonL10n.WEAPONS);
        beginSection(elements, CommonL10n.FIGHTER_WINGS);
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        ItemProvider itemProvider = new ItemProvider();
        CargoAPI cargo = itemProvider.getWeapons();
        cargo.addAll(itemProvider.getFighters());
        cargo.addAll(itemProvider.getModspecs());
        return new Cargo(cargo, "No matching items found.", size);
    }
}
