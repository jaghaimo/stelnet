package stelnet.ui;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class SimpleCallback implements Callable {

    public void cancel() {
    }

    public void confirm(IntelUIAPI ui) {
    }

    public boolean hasPrompt() {
        return false;
    }

    public void prompt(TooltipMakerAPI tooltipMaker) {
    }
}
