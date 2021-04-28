package stelnet.ui;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class AbstractButtonHandler implements ButtonHandler {

    public boolean hasPrompt() {
        return false;
    }

    public void onCancel(IntelUIAPI ui) {
    }

    public void onConfirm(IntelUIAPI ui) {
    }

    public void onPrompt(TooltipMakerAPI tooltipMaker) {
    }
}
