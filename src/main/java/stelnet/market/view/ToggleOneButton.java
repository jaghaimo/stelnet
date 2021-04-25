package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;
import stelnet.ui.Location;
import stelnet.ui.Position;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class ToggleOneButton extends ToggleButton {

    public ToggleOneButton(final IntelQuery query) {
        super(new Size(60, 24), "On", "Off", true, Misc.getButtonTextColor(), Misc.getGrayColor(), query.isEnabled());
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(10, -24));
        setCallback(new SimpleCallback() {
            @Override
            public void confirm(IntelUIAPI ui) {
                query.toggle();
            }
        });
    }
}
