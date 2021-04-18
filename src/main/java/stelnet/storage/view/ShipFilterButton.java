package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.Callable;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(String name, final FleetMemberFilter filter) {
        super(name);
        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                FilterManager filterManager = getFilterManager();
                if (isOn()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }

            @Override
            public void cancel() {
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
            }
        });
    }
}
