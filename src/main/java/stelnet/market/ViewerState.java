package stelnet.market;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.RenderableState;
import stelnet.market.data.MarketProvider;
import stelnet.market.view.MarketSelectButton;
import stelnet.market.view.ViewerTabViewFactory;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.SubmarketDataRenderer;
import uilib.Renderable;
import uilib.property.Size;

@Getter
@Setter
public class ViewerState implements RenderableState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private SubmarketDataRenderer activeTab = SubmarketDataRenderer.ITEMS;
    private MarketProvider marketProvider = new MarketProvider(null);

    @Override
    public List<Renderable> toRenderables(Size size) {
        return Arrays.<Renderable>asList(
            new ViewerTabViewFactory(this).createContainer(size),
            new MarketSelectButton()
        );
    }
}
