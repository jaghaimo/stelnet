package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.BoardInfo;

/**
 * A set of unique results for a given star system.
 */
@Getter
@RequiredArgsConstructor
public class ResultSet {

    private final QueryGrouping groupingStrategy;
    private final MarketAPI market;
    private final StarSystemAPI system;
    private final Set<MarketAPI> marketSet = new HashSet<>();
    private final Set<Result> resultSet = new TreeSet<>(
        new Comparator<Result>() {
            @Override
            public int compare(Result o1, Result o2) {
                return o1.compareTo(o2);
            }
        }
    );

    public ResultSet(QueryGrouping groupingStrategy, MarketAPI market) {
        this(groupingStrategy, market, market.getStarSystem());
        marketSet.add(market);
    }

    public void add(Result result) {
        marketSet.add(result.getMarket());
        resultSet.add(result);
    }

    public void add(ResultSet newResultSet) {
        marketSet.addAll(newResultSet.getMarketSet());
        resultSet.addAll(newResultSet.getResultSet());
    }

    public void addCargoStacks(MarketAPI market, SubmarketAPI submarket, List<CargoStackAPI> cargoStacks) {
        for (CargoStackAPI cargoStack : cargoStacks) {
            add(new Result(market, submarket, cargoStack));
        }
    }

    public void addFleetMembers(MarketAPI market, SubmarketAPI submarket, List<FleetMemberAPI> fleetMembers) {
        for (FleetMemberAPI fleetMember : fleetMembers) {
            add(new Result(market, submarket, fleetMember));
        }
    }

    public void addPeople(MarketAPI market, List<PersonAPI> people) {
        for (PersonAPI person : people) {
            add(new Result(market, person));
        }
    }

    public BoardInfo getBoardInfo() {
        return groupingStrategy.getBoardInfo(this);
    }

    public FactionAPI getFaction() {
        return groupingStrategy.getFaction(this);
    }

    public int getResultNumber() {
        return resultSet.size();
    }

    public SectorEntityToken getToken() {
        return groupingStrategy.getToken(this);
    }

    public void refresh() {
        marketSet.clear();
        for (Result result : resultSet) {
            marketSet.add(result.getMarket());
        }
    }
}
