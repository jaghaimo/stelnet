package uilib2.intel.actions;

import com.fs.starfarer.api.ui.IntelUIAPI;
import uilib2.intel.IntelUiAction;

public class RecreateIntel implements IntelUiAction {

    @Override
    public void act(IntelUIAPI ui) {
        ui.recreateIntelUI();
    }
}
