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

    public Set<MarketAPI> getMarkets(ResultSet resultSet) {
        Set<MarketAPI> markets = new LinkedHashSet<>();
        for (Result result : resultSet.getResultSet()) {
            markets.add(result.getMarket());
        }
        return markets;
    }

    public Set<SubmarketAPI> getSubmarkets(ResultSet resultSet, MarketAPI market) {
        Set<SubmarketAPI> submarkets = new LinkedHashSet<>();
        for (Result result : resultSet.getResultSet()) {
            SubmarketAPI submarket = result.getSubmarket();
            if (result.getMarket() == market && submarket != null) {
                submarkets.add(submarket);
            }
        }
        return submarkets;
    }

    public List<PersonAPI> getPeople(ResultSet resultSet, MarketAPI market) {
        List<PersonAPI> people = new LinkedList<>();
        for (Result result : resultSet.getResultSet()) {
            if (result.getMarket() == market && result.isPerson()) {
                people.add(result.getPerson());
            }
        }
        return people;
    }

    public List<CargoStackAPI> getItems(ResultSet resultSet, MarketAPI market) {
        List<CargoStackAPI> items = new LinkedList<>();
        for (Result result : resultSet.getResultSet()) {
            if (result.getMarket() == market && result.isCargoStack()) {
                items.add(result.getCargoStack());
            }
        }
        return items;
    }

    public List<FleetMemberAPI> getShips(ResultSet resultSet, MarketAPI market) {
        List<FleetMemberAPI> ships = new LinkedList<>();
        for (Result result : resultSet.getResultSet()) {
            if (result.getMarket() == market && result.isFleetMember()) {
                ships.add(result.getFleetMember());
            }
        }
        return ships;
    }
}
