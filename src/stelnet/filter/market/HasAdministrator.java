package stelnet.filter.market;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.List;
import stelnet.filter.market.person.IsPostedAs;
import stelnet.filter.market.person.PersonFilter;
import stelnet.util.CollectionReducer;

public class HasAdministrator implements MarketFilter {

    private final PersonFilter filter = IsPostedAs.admin();

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionReducer.reduce(people, filter);
        return !people.isEmpty();
    }
}
