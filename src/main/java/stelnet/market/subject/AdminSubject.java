package stelnet.market.subject;

import java.util.Arrays;
import java.util.List;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import stelnet.filter.market.HasAdministrator;
import stelnet.filter.market.MarketFilter;
import stelnet.filter.person.IsFreelanceAdmin;
import stelnet.filter.person.PersonFilter;

public class AdminSubject extends PersonSubject {

    public AdminSubject(MarketAPI m) {
        super("freelance administrator", m);
    }

    @Override
    public boolean canAcquire() {
        return true;
    }

    @Override
    public boolean isAvailable() {
        MarketFilter filter = new HasAdministrator();
        return filter.accept(market);
    }

    @Override
    protected List<PersonFilter> getFilters() {
        return Arrays.<PersonFilter>asList(new IsFreelanceAdmin());
    }
}
