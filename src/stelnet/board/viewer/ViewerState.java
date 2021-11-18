package stelnet.board.viewer;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.util.MarketUtils;
import stelnet.widget.viewer.ContentRenderer;
import stelnet.widget.viewer.FilteringButtons;
import stelnet.widget.viewer.InMarketStrategy;
import stelnet.widget.viewer.MarketViewState;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class ViewerState implements RenderableState, MarketViewState {

    private final FilteringButtons filteringButtons = new FilteringButtons();
    private ContentRenderer contentRenderer = ContentRenderer.ITEMS;
    private InMarketStrategy displayStrategy = new InMarketStrategy(null);

    @Override
    public List<Renderable> toRenderableList(Size size) {
        List<SectorEntityToken> entities = MarketUtils.convertMarketsToTokens(MarketUtils.getMarkets());
        return new ViewerView(entities, this).create(size);
    }
}
