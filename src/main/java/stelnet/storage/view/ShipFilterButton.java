package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.EventHandler;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(String translationId, final FleetMemberFilter filter) {
        super(translationId);
        setHandler(new EventHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                FilterManager filterManager = getFilterManager();
                if (isChecked()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }
        });
    }
}
