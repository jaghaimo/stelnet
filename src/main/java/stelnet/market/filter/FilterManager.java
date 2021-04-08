package stelnet.market.filter;

import java.util.List;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.filter.market.MarketFilter;

public interface FilterManager {

    public List<CargoStackFilter> listCargoFilters();

    public List<FleetMemberFilter> listFleetFilters();

    public List<MarketFilter> listStaffFilters();
}
