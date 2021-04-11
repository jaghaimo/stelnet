package stelnet.storage;

import java.util.HashSet;
import java.util.Set;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;

public class FilterManager {

    private Set<CargoStackFilter> cargoFilters;
    private Set<FleetMemberFilter> fleetFilters;

    public FilterManager() {
        cargoFilters = new HashSet<>();
        fleetFilters = new HashSet<>();
    }

    public void addFilter(CargoStackFilter filter) {
        cargoFilters.add(filter);
    }

    public void addFilter(FleetMemberFilter filter) {
        fleetFilters.add(filter);
    }

    public Set<CargoStackFilter> getCargoFilters() {
        return cargoFilters;
    }

    public Set<FleetMemberFilter> getFleetMemberFilters() {
        return fleetFilters;
    }

    public void removeFilter(CargoStackFilter filter) {
        cargoFilters.remove(filter);
    }

    public void removeFilter(FleetMemberFilter filter) {
        fleetFilters.remove(filter);
    }
}
