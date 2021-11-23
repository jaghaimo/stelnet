package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.board.query.ResultSet;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;

public class PeopleProvider extends QueryProvider {

    private static List<PersonAPI> people;

    public static void reset() {
        people = null;
    }

    public PeopleProvider(QueryFactory factory) {
        super(factory);
    }

    @Override
    public List<PersonAPI> getMatching(Set<Filter> filters) {
        List<MarketAPI> markets = MarketProvider.getMarkets(true);
        List<PersonAPI> people = extractPeople(markets);
        List<PersonAPI> peopleCopy = new LinkedList<>(people);
        CollectionUtils.reduce(peopleCopy, filters);
        return peopleCopy;
    }

    @Override
    protected void processMarkets(
        List<ResultSet> resultSets,
        List<MarketAPI> markets,
        Set<Filter> filters,
        final boolean groupBySystem
    ) {
        for (MarketAPI market : markets) {
            List<PersonAPI> people = market.getPeopleCopy();
            CollectionUtils.reduce(people, filters);
            ResultSet resultSet = new ResultSet(groupBySystem, market);
            resultSet.addPeople(market, people);
            addToResultSets(resultSets, resultSet);
        }
    }

    private List<PersonAPI> extractPeople(List<MarketAPI> markets) {
        if (people == null) {
            people = new LinkedList<>();
            for (MarketAPI market : markets) {
                people.addAll(market.getPeopleCopy());
            }
        }
        return people;
    }
}
