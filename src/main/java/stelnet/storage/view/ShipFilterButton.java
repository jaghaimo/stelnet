package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.SimpleHandler;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(String name, final FleetMemberFilter filter) {
        super(name);
        setHandler(new SimpleHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
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
