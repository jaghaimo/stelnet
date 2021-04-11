package stelnet.storage.element;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.Callable;

public class FleetFilterButton extends FilteringButton {

    public FleetFilterButton(String name, final FleetMemberFilter filter, boolean isOn) {
        super(name, isOn);
        setCallback(new Callable() {

            @Override
            public void callback() {
                FilterManager filterManager = getFilterManager();
                filterManager.addFilter(filter);
            }
        });
    }
}
