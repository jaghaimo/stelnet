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
import stelnet.util.MarketUtils;

public class PeopleProvider extends QueryProvider {

    public PeopleProvider(QueryFactory factory) {
        super(factory);
    }

    @Override
    public List<PersonAPI> getMatching(Set<Filter> filters) {
        List<MarketAPI> markets = MarketUtils.getMarkets();
        List<PersonAPI> people = extractPeople(markets);
        CollectionUtils.reduce(people, filters);
        return people;
    }

    @Override
    protected void processMarkets(List<ResultSet> resultSets, List<MarketAPI> markets, Set<Filter> filters) {
        for (MarketAPI market : markets) {
            List<PersonAPI> people = market.getPeopleCopy();
            CollectionUtils.reduce(people, filters);
            ResultSet resultSet = new ResultSet(market);
            resultSet.addPeople(market, people);
            addToResultSets(resultSets, resultSet);
        }
    }

    private List<PersonAPI> extractPeople(List<MarketAPI> markets) {
        List<PersonAPI> people = new LinkedList<>();
        for (MarketAPI market : markets) {
            people.addAll(market.getPeopleCopy());
        }
        return people;
    }
}
