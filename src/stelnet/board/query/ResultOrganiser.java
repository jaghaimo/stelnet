package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ResultOrganiser {

    public Set<MarketAPI> getMarkets(final ResultSet resultSet) {
        final Set<MarketAPI> markets = new LinkedHashSet<>();
        for (final Result result : resultSet.getResultSet()) {
            markets.add(result.getMarket());
        }
        return markets;
    }

    public Set<SubmarketAPI> getSubmarkets(final ResultSet resultSet, final MarketAPI market) {
        final Set<SubmarketAPI> submarkets = new LinkedHashSet<>();
        for (final Result result : resultSet.getResultSet()) {
            final SubmarketAPI submarket = result.getSubmarket();
            if (result.getMarket() == market && submarket != null) {
                submarkets.add(submarket);
            }
        }
        return submarkets;
    }

    public List<PersonAPI> getPeople(final ResultSet resultSet, final MarketAPI market) {
        final List<PersonAPI> people = new LinkedList<>();
        for (final Result result : resultSet.getResultSet()) {
            if (result.getMarket() == market && result.isPerson()) {
                people.add(result.getPerson());
            }
        }
        return people;
    }

    public List<CargoStackAPI> getItems(
        final ResultSet resultSet,
        final MarketAPI market,
        final SubmarketAPI submarket
    ) {
        final List<CargoStackAPI> items = new LinkedList<>();
        for (final Result result : resultSet.getResultSet()) {
            if (matches(result, market, submarket) && result.isCargoStack()) {
                items.add(result.getCargoStack());
            }
        }
        return items;
    }

    public List<FleetMemberAPI> getShips(
        final ResultSet resultSet,
        final MarketAPI market,
        final SubmarketAPI submarket
    ) {
        final List<FleetMemberAPI> ships = new LinkedList<>();
        for (final Result result : resultSet.getResultSet()) {
            if (matches(result, market, submarket) && result.isFleetMember()) {
                ships.add(result.getFleetMember());
            }
        }
        return ships;
    }

    private boolean matches(final Result result, final MarketAPI market, final SubmarketAPI submarket) {
        final boolean matchesMarket = result.getMarket() == market;
        final boolean matchesSubmarket = result.getSubmarket() == submarket || submarket == null;
        return matchesMarket && matchesSubmarket;
    }
}
