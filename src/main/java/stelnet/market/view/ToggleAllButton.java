package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class ToggleAllButton extends Button {

    public ToggleAllButton() {
        super(new Size(120, 24), "Toggle All", true, Misc.getButtonTextColor());
    }
}
