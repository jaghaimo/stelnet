package stelnet.board.market;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.market.data.InMarketStrategy;
import stelnet.board.market.view.MarketSelectButton;
import stelnet.board.market.view.ViewerTabViewFactory;
import stelnet.board.storage.ButtonManager;
import stelnet.board.storage.FilterManager;
import stelnet.board.storage.SubmarketDataRenderer;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class ViewerState implements RenderableState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private SubmarketDataRenderer activeTab = SubmarketDataRenderer.ITEMS;
    private InMarketStrategy marketProvider = new InMarketStrategy(null);

    @Override
    public List<Renderable> toRenderables(Size size) {
        return Arrays.<Renderable>asList(
            new ViewerTabViewFactory(this).createContainer(size),
            new MarketSelectButton()
        );
    }
}
