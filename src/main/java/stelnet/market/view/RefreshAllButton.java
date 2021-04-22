package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class RefreshAllButton extends Button {

    public RefreshAllButton(boolean isEnabled) {
        super(new Size(120, 24), "Refresh All", isEnabled, Misc.getButtonTextColor());
    }
}
