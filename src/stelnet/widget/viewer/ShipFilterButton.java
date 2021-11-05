package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.FleetMemberFilter;
import uilib.EventHandler;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(
        final FilteringButtons filteringButtons,
        Enum<?> translationId,
        final FleetMemberFilter filter
    ) {
        super(translationId);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    if (isStateOn()) {
                        filteringButtons.removeFilter(filter);
                    } else {
                        filteringButtons.addFilter(filter);
                    }
                }
            }
        );
    }
}
