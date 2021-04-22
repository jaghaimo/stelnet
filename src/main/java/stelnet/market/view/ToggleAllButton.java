package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class ToggleAllButton extends Button {

    public ToggleAllButton(boolean isEnabled) {
        super(new Size(120, 24), "Toggle All", isEnabled, Misc.getButtonTextColor());
    }
}
