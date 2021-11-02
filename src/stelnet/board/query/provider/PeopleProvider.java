package stelnet.board.query.provider;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import stelnet.filter.Filter;
import stelnet.util.CollectionUtils;
import stelnet.util.MarketUtils;

public class PeopleProvider {

    public List<PersonAPI> getPeople() {
        return getPeople(Collections.<Filter>emptyList());
    }

    public List<PersonAPI> getPeople(List<Filter> filters) {
        List<MarketAPI> markets = MarketUtils.getMarkets();
        List<PersonAPI> people = extractPeople(markets);
        CollectionUtils.reduce(people, filters);
        return people;
    }

    private List<PersonAPI> extractPeople(List<MarketAPI> markets) {
        List<PersonAPI> people = new LinkedList<>();
        for (MarketAPI market : markets) {
            people.addAll(market.getPeopleCopy());
        }
        return people;
    }
}
