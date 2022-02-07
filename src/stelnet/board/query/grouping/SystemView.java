package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import stelnet.board.query.view.result.MarketHeader;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class SystemView extends ResultView {

    public SystemView(ResultIntel intel, ResultSet resultSet) {
        super(intel, resultSet);
    }

    protected void addResults(List<Renderable> elements, float width) {
        for (MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            elements.add(new MarketHeader(market, intel));
            addPeople(elements, market, width);
            addItems(elements, market, width);
            addShips(elements, market, width);
            elements.add(new Spacer(2 * UiConstants.DEFAULT_SPACER));
        }
    }

    private void addItems(List<Renderable> elements, MarketAPI market, float width) {
        List<CargoStackAPI> items = resultOrganiser.getItems(resultSet, market, null);
        if (items.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
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

    private void addShips(List<Renderable> elements, MarketAPI market, float width) {
        List<FleetMemberAPI> ships = resultOrganiser.getShips(resultSet, market, null);
        if (ships.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
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
