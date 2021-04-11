package stelnet.storage.element;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.Callable;

public class CargoFilterButton extends FilteringButton {

    public CargoFilterButton(String name, final CargoStackFilter filter, boolean isOn) {
        super(name, isOn);
        setCallback(new Callable() {

            @Override
            public void callback() {
                FilterManager filterManager = getFilterManager();
                filterManager.addFilter(filter);
            }
        });
    }
}
