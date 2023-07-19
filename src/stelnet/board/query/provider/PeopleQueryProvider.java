package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import stelnet.board.query.ResultSet;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.L10n;
import uilib.RenderableShowComponent;
import uilib.ShowPeople;
import uilib.property.Size;

public class PeopleQueryProvider extends QueryProvider {

    private static List<PersonAPI> people;

    public static void resetCache() {
        people = null;
    }

    @Override
    public List<PersonAPI> getMatching(final Set<Filter> filters) {
        final List<MarketAPI> markets = MarketProvider.getMarkets(true);
        final List<PersonAPI> people = extractPeople(markets);
        final List<PersonAPI> peopleCopy = new LinkedList<>(people);
        CollectionUtils.reduce(peopleCopy, filters);
        return peopleCopy;
    }

    @Override
    public RenderableShowComponent getPreview(final Set<Filter> filters, final Size size) {
        return new ShowPeople(getMatching(filters), L10n.query("NO_MATCHING_PEOPLE"), size);
    }

    @Override
    protected void processMarkets(
        final List<ResultSet> resultSets,
        final List<MarketAPI> markets,
        final Set<Filter> filters,
        final GroupingStrategy groupingStrategy
    ) {
        for (final MarketAPI market : markets) {
            final List<PersonAPI> people = market.getPeopleCopy();
            CollectionUtils.reduce(people, filters);
            final ResultSet resultSet = new ResultSet(groupingStrategy, market);
            resultSet.addPeople(market, people);
            addToResultSets(resultSets, resultSet);
        }
    }

    private List<PersonAPI> extractPeople(final List<MarketAPI> markets) {
        if (people == null) {
            people = new LinkedList<>();
            for (final MarketAPI market : markets) {
                people.addAll(market.getPeopleCopy());
            }
        }
        return people;
    }
}
