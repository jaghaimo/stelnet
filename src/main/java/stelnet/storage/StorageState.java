package stelnet.storage;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.RenderableState;
import stelnet.storage.view.StorageTabViewFactory;
import uilib.Renderable;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private SubmarketDataRenderer activeTab = SubmarketDataRenderer.ITEMS;
    private SubmarketDataStrategy activeView = SubmarketDataStrategy.UNIFIED;

    @Override
    public List<Renderable> toRenderables(Size size) {
        return Arrays.<Renderable>asList(
            new StorageTabViewFactory(this).createContainer(size),
            activeView.getNextButton()
        );
    }
}
