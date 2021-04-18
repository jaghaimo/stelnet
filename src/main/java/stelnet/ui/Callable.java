package stelnet.ui;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public interface Callable {

    public void cancel();

    public void confirm(IntelUIAPI ui);

    public void prompt(TooltipMakerAPI tooltipMaker);
}
