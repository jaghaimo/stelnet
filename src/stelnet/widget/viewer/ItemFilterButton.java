package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.filter.Filter;
import uilib.EventHandler;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(final ButtonManager filteringButtons, final String title, final Filter filter) {
        super(title);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
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
