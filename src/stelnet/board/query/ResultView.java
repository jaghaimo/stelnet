package stelnet.board.query;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.result.MarketHeader;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class ResultView implements RenderableFactory {

    protected final ResultIntel intel;
    protected final ResultSet resultSet;
    protected final ResultOrganiser resultOrganiser = new ResultOrganiser();

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> elements = new LinkedList<>();
        addMarkets(elements, width);
        return elements;
    }

    protected void addMarkets(List<Renderable> elements, float width) {
        for (MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            elements.add(new MarketHeader(market, intel));
            addPeople(elements, market, width);
            addItems(elements, market, width);
            addShips(elements, market, width);
            elements.add(new Spacer(20));
        }
    }

    protected abstract void addPeople(List<Renderable> elements, MarketAPI market, float width);

    protected abstract void addItems(List<Renderable> elements, MarketAPI market, float width);

    protected abstract void addShips(List<Renderable> elements, MarketAPI market, float width);

    protected Color getSupportColor(FactionAPI faction) {
        return Misc.scaleAlpha(faction.getBaseUIColor(), 0.5f);
    }
}
