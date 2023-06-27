package stelnet.board.query;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.ShowPeople;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public abstract class ResultView implements RenderableFactory {

    protected final ResultIntel intel;
    protected final ResultSet resultSet;
    protected final ResultOrganiser resultOrganiser = new ResultOrganiser();

    @Override
    public List<Renderable> create(final Size size) {
        final float width = size.getWidth();
        final List<Renderable> elements = new LinkedList<>();
        addResults(elements, width);
        return elements;
    }

    protected void addPeople(final List<Renderable> elements, final MarketAPI market, final float width) {
        final List<PersonAPI> people = resultOrganiser.getPeople(resultSet, market);
        if (people.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        final ShowPeople showPeople = new ShowPeople(people, L10n.query("NO_MATCHING_PEOPLE"), new Size(width, 0));
        showPeople.setGroupColor(getSupportColor(market.getFaction()));
        elements.add(showPeople);
    }

    protected abstract void addResults(List<Renderable> elements, float width);

    protected Color getSupportColor(final FactionAPI faction) {
        return Misc.scaleAlpha(faction.getBaseUIColor(), 0.5f);
    }
}
