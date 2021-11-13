package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.view.result.ControlButtons;
import stelnet.util.CargoUtils;
import uilib.Heading;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.ShowCargo;
import uilib.ShowPeople;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.property.Size;

@RequiredArgsConstructor
public class ResultView implements RenderableFactory {

    private final ResultIntel intel;
    private final ResultSet resultSet;
    private final ResultOrganiser resultOrganiser = new ResultOrganiser();

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> renderables = new LinkedList<>();
        addMarkets(renderables, width);
        return renderables;
    }

    private void addMarkets(List<Renderable> renderables, float width) {
        for (MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            addMarket(renderables, market, width);
            addPeople(renderables, market, width);
            addItems(renderables, market, width);
            addShips(renderables, market, width);
            renderables.add(new Spacer(20));
        }
    }

    private void addMarket(List<Renderable> renderables, MarketAPI market, float width) {
        FactionAPI faction = market.getFaction();
        renderables.add(new Heading(market.getName(), faction.getBaseUIColor(), faction.getDarkUIColor()));
        renderables.add(new ControlButtons(market, intel, width));
    }

    private void addPeople(List<Renderable> renderables, MarketAPI market, float width) {
        List<PersonAPI> people = resultOrganiser.getPeople(resultSet, market);
        if (people.isEmpty()) {
            return;
        }
        renderables.add(new Spacer(10));
        renderables.add(new ShowPeople(people, "No people found", new Size(width, 0)));
    }

    private void addItems(List<Renderable> renderables, MarketAPI market, float width) {
        List<CargoStackAPI> items = resultOrganiser.getItems(resultSet, market);
        if (items.isEmpty()) {
            return;
        }
        renderables.add(new Spacer(10));
        CargoAPI cargo = CargoUtils.makeCargoFromStacks(items);
        renderables.add(new ShowCargo(cargo, "Items", "No items found", new Size(width, 0)));
    }

    private void addShips(List<Renderable> renderables, MarketAPI market, float width) {
        List<FleetMemberAPI> ships = resultOrganiser.getShips(resultSet, market);
        if (ships.isEmpty()) {
            return;
        }
        renderables.add(new Spacer(10));
        renderables.add(new ShowShips(ships, "Ships", "No ships found", new Size(width, 0)));
    }
}
