package stelnet.filter.market;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.person.HasPersonality;
import stelnet.filter.person.IsOfficer;
import stelnet.filter.person.PersonFilter;
import stelnet.util.CollectionReducer;

public class HasOfficer implements MarketFilter {

    private final List<PersonFilter> filters;

    public HasOfficer(String personality) {
        filters = Arrays.asList(new IsOfficer(), new HasPersonality(personality));
    }

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionReducer.reduce(people, filters);
        return !people.isEmpty();
    }
}
