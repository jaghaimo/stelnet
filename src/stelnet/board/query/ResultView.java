package stelnet.board.query;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import uilib.Renderable;
import uilib.RenderableFactory;
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
        addResults(elements, width);
        return elements;
    }

    protected abstract void addResults(List<Renderable> elements, float width);

    protected Color getSupportColor(FactionAPI faction) {
        return Misc.scaleAlpha(faction.getBaseUIColor(), 0.5f);
    }
}
