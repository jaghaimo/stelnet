package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.AbstractButtonHandler;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(String name, final CargoStackFilter filter) {
        super(name);
        setHandler(new AbstractButtonHandler() {

            @Override
            public void onConfirm(IntelUIAPI ui) {
                FilterManager filterManager = getFilterManager();
                if (isToggledOn()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }
        });
    }
}
