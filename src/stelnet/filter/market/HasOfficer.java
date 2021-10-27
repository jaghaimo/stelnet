package stelnet.filter.market;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Arrays;
import java.util.List;
import stelnet.filter.market.person.HasPersonality;
import stelnet.filter.market.person.IsPostedAs;
import stelnet.filter.market.person.PersonFilter;
import stelnet.util.CollectionReducer;

public class HasOfficer implements MarketFilter {

    private final List<PersonFilter> filters;

    public HasOfficer(String personality) {
        filters = Arrays.asList(IsPostedAs.officer(), new HasPersonality(personality));
    }

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionReducer.reduce(people, filters);
        return !people.isEmpty();
    }
}
