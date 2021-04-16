package stelnet.storage.view;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.Callable;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(String name, final FleetMemberFilter filter) {
        super(name);
        setCallback(new Callable() {

            @Override
            public void callback() {
                FilterManager filterManager = getFilterManager();
                if (isOn()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }
        });
    }
}
