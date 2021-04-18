package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.storage.FilterManager;
import stelnet.ui.Callable;

public class ItemFilterButton extends FilteringButton {

    public ItemFilterButton(String name, final CargoStackFilter filter) {
        super(name);
        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                FilterManager filterManager = getFilterManager();
                if (isOn()) {
                    filterManager.removeFilter(filter);
                } else {
                    filterManager.addFilter(filter);
                }
            }

            @Override
            public void cancel() {
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
            }
        });
    }
}
