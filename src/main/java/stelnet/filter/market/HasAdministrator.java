package stelnet.filter.market;

import java.util.List;

import com.fs.starfarer.api.campaign.CommDirectoryEntryAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.person.IsFreelanceAdmin;
import stelnet.filter.person.PersonFilter;
import stelnet.helper.CollectionHelper;

public class HasAdministrator implements MarketFilter {

    private final PersonFilter filter;

    public HasAdministrator() {
        filter = new IsFreelanceAdmin();
    }

    public boolean accept(MarketAPI market) {
        List<CommDirectoryEntryAPI> people = market.getCommDirectory().getEntriesCopy();
        CollectionHelper.reduce(people, filter);
        return !people.isEmpty();
    }
}
