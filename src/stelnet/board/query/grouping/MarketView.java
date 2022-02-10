package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import stelnet.util.CargoUtils;
import stelnet.util.L10n;
import stelnet.widget.heading.MarketHeader;
import uilib.Renderable;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class MarketView extends ResultView {

    public MarketView(ResultIntel intel, ResultSet resultSet) {
        super(intel, resultSet);
    }

    @Override
    protected void addResults(List<Renderable> elements, float width) {
        for (MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            elements.add(new MarketHeader(market, intel));
            addPeople(elements, market, width);
            addSubmarkets(elements, market, width);
            elements.add(new Spacer(2 * UiConstants.DEFAULT_SPACER));
        }
    }

    protected void addSubmarkets(List<Renderable> elements, MarketAPI market, float width) {
        for (SubmarketAPI submarket : resultOrganiser.getSubmarkets(resultSet, market)) {
            addItems(elements, market, submarket, width);
            addShips(elements, market, submarket, width);
        }
    }

    private void addItems(List<Renderable> elements, MarketAPI market, SubmarketAPI submarket, float width) {
        List<CargoStackAPI> items = resultOrganiser.getItems(resultSet, market, submarket);
        if (items.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        CargoAPI cargo = CargoUtils.makeCargoFromStacks(items);
        ShowCargo showCargo = new ShowCargo(
            cargo,
            L10n.get(QueryL10n.SUBMARKET_ITEMS, submarket.getNameOneLine()),
            L10n.get(QueryL10n.NO_MATCHING_ITEMS),
            new Size(width, 0)
        );
        showCargo.setTitleColor(getSupportColor(submarket.getFaction()));
        elements.add(showCargo);
    }

    private void addShips(List<Renderable> elements, MarketAPI market, SubmarketAPI submarket, float width) {
        List<FleetMemberAPI> ships = resultOrganiser.getShips(resultSet, market, submarket);
        if (ships.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        ShowShips showShips = new ShowShips(
            ships,
            L10n.get(QueryL10n.SUBMARKET_SHIPS, submarket.getNameOneLine()),
            L10n.get(QueryL10n.NO_MATCHING_SHIPS),
            new Size(width, 0)
        );
        showShips.setTitleColor(getSupportColor(submarket.getFaction()));
        elements.add(showShips);
    }
}
