package stelnet.storage.element;

import java.awt.Color;

import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayGroupButton extends ToggleButton {

    public DisplayGroupButton(Size size, boolean isEnabled, Color color, boolean isOn) {
        super(size, "Group By Location", "Disable Grouping", isEnabled, color, color, isOn);
    }
}
