package stelnet.board.market;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.market.view.MarketSelectButton;
import stelnet.board.market.view.ViewerTabViewFactory;
import stelnet.util.MarketUtils;
import stelnet.view.market.ContentRenderer;
import stelnet.view.market.FilteringButtons;
import stelnet.view.market.InMarketStrategy;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class ViewerState implements RenderableState {

    private final FilteringButtons filteringButtons = new FilteringButtons();
    private ContentRenderer activeRenderer = ContentRenderer.ITEMS;
    private InMarketStrategy marketProvider = new InMarketStrategy(null);

    @Override
    public List<Renderable> toRenderables(Size size) {
        List<SectorEntityToken> entities = MarketUtils.convertMarketsToTokens(MarketUtils.getMarkets());
        return Arrays.<Renderable>asList(
            new ViewerTabViewFactory(this).createContainer(size),
            new MarketSelectButton(entities)
        );
    }
}
