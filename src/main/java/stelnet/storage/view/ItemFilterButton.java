package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.SimpleCallback;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(String name, final CargoStackFilter filter) {
        super(name);
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                FilterManager filterManager = getFilterManager();
                if (isOn()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }
        });
    }
}
