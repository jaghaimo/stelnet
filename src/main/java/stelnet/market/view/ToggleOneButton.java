package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class ToggleOneButton extends ToggleButton {

    public ToggleOneButton(final IntelQuery query) {
        super(new Size(80, 24), "On", "Off", true, Misc.getButtonTextColor(), Misc.getButtonTextColor(),
                query.isEnabled());
        setCallback(new SimpleCallback() {
            @Override
            public void confirm(IntelUIAPI ui) {
                query.toggle();
            }
        });
    }
}
