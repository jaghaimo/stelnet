package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class ToggleOneButton extends ToggleButton {

    public ToggleOneButton(boolean isOn) {
        super(new Size(80, 24), "On", "Off", true, Misc.getButtonTextColor(), Misc.getButtonTextColor(), isOn);
    }
}
