package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.EventHandler;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(String name, final CargoStackFilter filter) {
        super(name);
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
