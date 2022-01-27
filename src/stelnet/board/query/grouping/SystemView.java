package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.ShowCargo;
import uilib.ShowPeople;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.property.Size;

public class SystemView extends ResultView {

    public SystemView(ResultIntel intel, ResultSet resultSet) {
        super(intel, resultSet);
    }

    @Override
    protected void addPeople(List<Renderable> elements, MarketAPI market, float width) {
        List<PersonAPI> people = resultOrganiser.getPeople(resultSet, market);
        if (people.isEmpty()) {
            return;
        }
        elements.add(new Spacer(10));
        ShowPeople showPeople = new ShowPeople(people, L10n.get(QueryL10n.NO_MATCHING_PEOPLE), new Size(width, 0));
        showPeople.setGroupColor(getSupportColor(market.getFaction()));
        elements.add(showPeople);
    }

    @Override
    protected void addItems(List<Renderable> elements, MarketAPI market, float width) {
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
        showCargo.setTitleColor(getSupportColor(market.getFaction()));
        elements.add(showCargo);
    }

    @Override
    protected void addShips(List<Renderable> elements, MarketAPI market, float width) {
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
        showShips.setTitleColor(getSupportColor(market.getFaction()));
        elements.add(showShips);
    }
}
