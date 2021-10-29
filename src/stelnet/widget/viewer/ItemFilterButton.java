package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.CargoStackFilter;
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
