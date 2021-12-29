package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.result.MarketHeader;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.ShowCargo;
import uilib.ShowPeople;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.property.Size;

@RequiredArgsConstructor
public class MultiResultView implements RenderableFactory {

    private final MultiResultIntel intel;
    private final ResultSet resultSet;
    private final ResultOrganiser resultOrganiser = new ResultOrganiser();

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> elements = new LinkedList<>();
        addMarkets(elements, width);
        return elements;
    }

    private void addMarkets(List<Renderable> elements, float width) {
        for (MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            elements.add(new MarketHeader(market, intel));
            addPeople(elements, market, width);
            addItems(elements, market, width);
            addShips(elements, market, width);
            elements.add(new Spacer(20));
        }
    }

    private void addPeople(List<Renderable> elements, MarketAPI market, float width) {
        List<PersonAPI> people = resultOrganiser.getPeople(resultSet, market);
        if (people.isEmpty()) {
            return;
        }
        elements.add(new Spacer(10));
        ShowPeople showPeople = new ShowPeople(people, L10n.get(QueryL10n.NO_MATCHING_PEOPLE), new Size(width, 0));
        showPeople.setGroupColor(getSupportColor(market));
        elements.add(showPeople);
    }

    private void addItems(List<Renderable> elements, MarketAPI market, float width) {
        List<CargoStackAPI> items = resultOrganiser.getItems(resultSet, market);
        if (items.isEmpty()) {
            return;
        }
        elements.add(new Spacer(10));
        CargoAPI cargo = CargoUtils.makeCargoFromStacks(items);
        ShowCargo showCargo = new ShowCargo(
            cargo,
            L10n.get(QueryL10n.MATCHING_ITEMS),
            L10n.get(QueryL10n.NO_MATCHING_ITEMS),
            new Size(width, 0)
        );
        showCargo.setTitleColor(getSupportColor(market));
        elements.add(showCargo);
    }

    private void addShips(List<Renderable> elements, MarketAPI market, float width) {
        List<FleetMemberAPI> ships = resultOrganiser.getShips(resultSet, market);
        if (ships.isEmpty()) {
            return;
        }
        elements.add(new Spacer(10));
        ShowShips showShips = new ShowShips(
            ships,
            L10n.get(QueryL10n.MATCHING_SHIPS),
            L10n.get(QueryL10n.NO_MATCHING_SHIPS),
            new Size(width, 0)
        );
        showShips.setTitleColor(getSupportColor(market));
        elements.add(showShips);
    }

    private Color getSupportColor(MarketAPI market) {
        return Misc.scaleAlpha(market.getFaction().getBaseUIColor(), 0.5f);
    }
}
