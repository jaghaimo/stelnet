package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import uilib.EventHandler;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(
        final FilterManager filterManager,
        String translationId,
        final CargoStackFilter filter
    ) {
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
