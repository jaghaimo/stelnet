package stelnet.board.viewer;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.widget.viewer.MarketView;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class ViewerView implements RenderableFactory {

    private final List<SectorEntityToken> entities;
    private final ViewerState viewerState;

    @Override
    public List<Renderable> create(final Size size) {
        final List<Renderable> elements = new LinkedList<>();
        elements.addAll(new MarketView(viewerState).create(size));
        elements.add(new MarketSelectButton(entities));
        return elements;
    }
}
