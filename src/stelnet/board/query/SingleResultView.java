package stelnet.board.query;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.result.MarketHeader;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class SingleResultView implements RenderableFactory {

    private final SingleResultIntel intel;
    private final Result result;

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new MarketHeader(result.getMarket(), intel));
        return elements;
    }
}
