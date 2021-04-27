package stelnet.storage;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;

public class FilterManager {

    @Getter
    private Set<CargoStackFilter> itemFilters;

    @Getter
    private Set<FleetMemberFilter> shipFilters;

    public FilterManager() {
        itemFilters = new HashSet<>();
        shipFilters = new HashSet<>();
    }

    public void addFilter(CargoStackFilter filter) {
        itemFilters.add(filter);
    }

    public void addFilter(FleetMemberFilter filter) {
        shipFilters.add(filter);
    }

    public void removeFilter(CargoStackFilter filter) {
        itemFilters.remove(filter);
    }

    public void removeFilter(FleetMemberFilter filter) {
        shipFilters.remove(filter);
    }
}
