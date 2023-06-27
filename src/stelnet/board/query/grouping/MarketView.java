package stelnet.board.query.grouping;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.List;
import stelnet.board.query.ResultIntel;
import stelnet.board.query.ResultSet;
import stelnet.board.query.ResultView;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import stelnet.widget.heading.MarketHeader;
import uilib.Renderable;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

public class MarketView extends ResultView {

    public MarketView(final ResultIntel intel, final ResultSet resultSet) {
        super(intel, resultSet);
    }

    @Override
    protected void addResults(final List<Renderable> elements, final float width) {
        MarketHeader marketHeader;
        for (final MarketAPI market : resultOrganiser.getMarkets(resultSet)) {
            marketHeader = new MarketHeader(market, intel);
            marketHeader.getShowButton().setEnabled(false);
            elements.add(marketHeader);
            addPeople(elements, market, width);
            addSubmarkets(elements, market, width);
            elements.add(new Spacer(2 * UiConstants.DEFAULT_SPACER));
        }
    }

    protected void addSubmarkets(final List<Renderable> elements, final MarketAPI market, final float width) {
        for (final SubmarketAPI submarket : resultOrganiser.getSubmarkets(resultSet, market)) {
            addItems(elements, market, submarket, width);
            addShips(elements, market, submarket, width);
        }
    }

    private void addItems(
        final List<Renderable> elements,
        final MarketAPI market,
        final SubmarketAPI submarket,
        final float width
    ) {
        final List<CargoStackAPI> items = resultOrganiser.getItems(resultSet, market, submarket);
        if (items.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        final CargoAPI cargo = StelnetHelper.makeCargoFromStacks(items);
        final ShowCargo showCargo = new ShowCargo(
            cargo,
            L10n.query("SUBMARKET_ITEMS", submarket.getNameOneLine()),
            L10n.query("NO_MATCHING_ITEMS"),
            new Size(width, 0)
        );
        showCargo.setTitleColor(getSupportColor(submarket.getFaction()));
        elements.add(showCargo);
    }

    private void addShips(
        final List<Renderable> elements,
        final MarketAPI market,
        final SubmarketAPI submarket,
        final float width
    ) {
        final List<FleetMemberAPI> ships = resultOrganiser.getShips(resultSet, market, submarket);
        if (ships.isEmpty()) {
            return;
        }
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        final ShowShips showShips = new ShowShips(
            ships,
            L10n.query("SUBMARKET_SHIPS", submarket.getNameOneLine()),
            L10n.query("NO_MATCHING_SHIPS"),
            new Size(width, 0)
        );
        showShips.setTitleColor(getSupportColor(submarket.getFaction()));
        elements.add(showShips);
    }
}
