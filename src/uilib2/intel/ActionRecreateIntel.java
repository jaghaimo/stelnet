package uilib2.intel;

import com.fs.starfarer.api.ui.IntelUIAPI;

public class ActionRecreateIntel implements IntelUiAction {

    @Override
    public void act(IntelUIAPI ui) {
        ui.recreateIntelUI();
    }
}
