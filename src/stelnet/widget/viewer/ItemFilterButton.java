package stelnet.widget.market;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.cargostack.CargoStackFilter;
import uilib.EventHandler;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(
        final FilteringButtons filteringButtons,
        String translationId,
        final CargoStackFilter filter
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
