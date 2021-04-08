package stelnet.market.filter;

import java.util.List;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.market.MarketFilter;

public class ImmutableFilterManager implements FilterManager {

    private List<CargoStackFilter> cargoFilters;
    private List<FleetMemberFilter> fleetFilters;
    private List<MarketFilter> staffFilters;

    public ImmutableFilterManager(FilterManager filterManager) {
        cargoFilters = filterManager.listCargoFilters();
        fleetFilters = filterManager.listFleetFilters();
        staffFilters = filterManager.listStaffFilters();
    }

    public List<CargoStackFilter> listCargoFilters() {
        return cargoFilters;
    }

    public List<FleetMemberFilter> listFleetFilters() {
        return fleetFilters;
    }

    public List<MarketFilter> listStaffFilters() {
        return staffFilters;
    }
}
