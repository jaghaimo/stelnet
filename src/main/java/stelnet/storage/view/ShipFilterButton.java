package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.SimpleCallback;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(String name, final FleetMemberFilter filter) {
        super(name);
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
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
