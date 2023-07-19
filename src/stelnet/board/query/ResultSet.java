package stelnet.board.query;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.grouping.GroupingData;
import stelnet.board.query.grouping.GroupingStrategy;
import uilib.RenderableIntelInfo;

/**
 * A set of unique results for a given star system.
 */
@Getter
@RequiredArgsConstructor
public class ResultSet {

    private final GroupingStrategy groupingStrategy;
    private final MarketAPI market;
    private final StarSystemAPI system;
    private final Set<MarketAPI> marketSet = new HashSet<>();
    private final Set<Result> resultSet = new TreeSet<>(
        new Comparator<Result>() {
            @Override
            public int compare(final Result o1, final Result o2) {
                return o1.compareTo(o2);
            }
        }
    );

    public ResultSet(final GroupingStrategy groupingStrategy, final MarketAPI market) {
        this(groupingStrategy, market, market.getStarSystem());
        marketSet.add(market);
    }

    public void add(final Result result) {
        marketSet.add(result.getMarket());
        resultSet.add(result);
    }

    public void add(final ResultSet newResultSet) {
        marketSet.addAll(newResultSet.getMarketSet());
        resultSet.addAll(newResultSet.getResultSet());
    }

    public void addCargoStacks(
        final MarketAPI market,
        final SubmarketAPI submarket,
        final List<CargoStackAPI> cargoStacks
    ) {
        for (final CargoStackAPI cargoStack : cargoStacks) {
            add(new Result(market, submarket, cargoStack));
        }
    }

    public void addFleetMembers(
        final MarketAPI market,
        final SubmarketAPI submarket,
        final List<FleetMemberAPI> fleetMembers
    ) {
        for (final FleetMemberAPI fleetMember : fleetMembers) {
            add(new Result(market, submarket, fleetMember));
        }
    }

    public void addPeople(final MarketAPI market, final List<PersonAPI> people) {
        for (final PersonAPI person : people) {
            add(new Result(market, person));
        }
    }

    public RenderableIntelInfo getBoardInfo() {
        return getGroupingData().getInfo();
    }

    public FactionAPI getFaction() {
        return getGroupingData().getFaction();
    }

    public String getKey() {
        return getGroupingData().getKey();
    }

    public int getResultCount() {
        int i = 0;
        for (final Result result : resultSet) {
            i += result.getCount();
        }
        return i;
    }

    public SectorEntityToken getToken() {
        return getGroupingData().getToken();
    }

    public void refresh() {
        marketSet.clear();
        for (final Result result : resultSet) {
            marketSet.add(result.getMarket());
        }
    }

    public int size() {
        return resultSet.size();
    }

    private GroupingData getGroupingData() {
        return groupingStrategy.getGroupingData(this);
    }
}
