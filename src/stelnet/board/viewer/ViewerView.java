package stelnet.board.viewer;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.widget.market.DisplayStrategy;
import stelnet.widget.market.MarketView;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class ViewerView implements RenderableFactory {

    private final List<SectorEntityToken> entities;
    private final ViewerState viewerState;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.addAll(
            new MarketView(
                viewerState.getFilteringButtons(),
                viewerState.getActiveRenderer(),
                (DisplayStrategy) viewerState.getMarketProvider()
            )
                .create(size)
        );
        renderables.add(new MarketSelectButton(entities));
        return renderables;
    }
}
