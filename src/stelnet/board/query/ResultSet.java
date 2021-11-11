package stelnet.board.query;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import stelnet.util.StringUtils;
import uilib.TableContent;
import uilib.TableContentRow;

/**
 * A set of unique results for a given star system.
 */
@Log4j
@Getter
@RequiredArgsConstructor
public class ResultSet implements TableContent {

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

    public ResultSet(MarketAPI market, SubmarketAPI submarket, List<FleetMemberAPI> fleetMembers) {
        system = market.getStarSystem();
        marketSet.add(market);
        for (FleetMemberAPI fleetMember : fleetMembers) {
            resultSet.add(new Result(market, submarket, fleetMember));
        }
    }

    public void add(Result result) {
        if (system != result.getSystem()) {
            log.warn("Not adding one result to results due to incorrect system\n" + result.toString());
            return;
        }
        marketSet.add(result.getMarket());
        resultSet.add(result);
    }

    public void add(ResultSet newResultSet) {
        if (system != newResultSet.getSystem()) {
            log.warn("Not adding result set to results due to incorrect system\n" + newResultSet.toString());
            return;
        }
        marketSet.addAll(newResultSet.getMarketSet());
        resultSet.addAll(newResultSet.getResultSet());
    }

    public int getMarketNumber() {
        return marketSet.size();
    }

    public int getResultNumber() {
        return resultSet.size();
    }

    public String getSystemName() {
        return StringUtils.getStarSystem(system);
    }

    public SectorEntityToken getSystemToken() {
        if (system == null) {
            return null;
        }
        return system.getCenter();
    }

    @Override
    public Object[] getHeaders(float width) {
        return new Object[] {
            "Market Name",
            0.1 * width,
            "Submarket Name",
            0.1 * width,
            "Name",
            0.1 * width,
            "Type",
            0.1 * width,
        };
    }

    @Override
    public List<? extends TableContentRow> getRows() {
        return new LinkedList<TableContentRow>(resultSet);
    }
}
