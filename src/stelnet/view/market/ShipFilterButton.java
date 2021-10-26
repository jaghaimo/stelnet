package stelnet.view.market;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.fleetmember.FleetMemberFilter;
import uilib.EventHandler;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(final FilterManager filterManager, String translationId, final FleetMemberFilter filter) {
        super(translationId);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    if (isStateOn()) {
                        filterManager.removeFilter(filter);
                    } else {
                        filterManager.addFilter(filter);
                    }
                }
            }
        );
    }
}
