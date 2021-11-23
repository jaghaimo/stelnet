package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.Filter;
import uilib.EventHandler;

public class ShipFilterButton extends FilteringButton {

    public ShipFilterButton(final FilteringButtons filteringButtons, Enum<?> translationId, final Filter filter) {
        super(translationId);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    if (isStateOn()) {
                        filteringButtons.remove(filter);
                    } else {
                        filteringButtons.add(filter);
                    }
                }
            }
        );
    }
}
