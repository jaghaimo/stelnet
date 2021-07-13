package stelnet.market.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.intel.IntelQuery;
import stelnet.ui.EventHandler;
import stelnet.ui.ToggleButton;
import stelnet.ui.property.Location;
import stelnet.ui.property.Position;
import stelnet.ui.property.Size;

public class ToggleOneButton extends ToggleButton {

    public ToggleOneButton(final IntelQuery query) {
        super(new Size(60, 24), "On", "Off", true, Misc.getButtonTextColor(), Misc.getGrayColor(), query.isEnabled());
        setLocation(Location.TOP_RIGHT);
        setOffset(new Position(10, -24));
        setHandler(new EventHandler() {
            @Override
            public void onConfirm(IntelUIAPI ui) {
                query.toggle();
            }
        });
    }
}
