package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.ui.Location;
import stelnet.ui.SimpleCallback;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayViewButton extends ToggleButton {

    public DisplayViewButton() {
        super(new Size(180, 24), "Group By Location", "Disable Grouping", true, Misc.getButtonTextColor(),
                Misc.getButtonTextColor(), true);
        setLocation(Location.TOP_RIGHT);
        setCallback(new SimpleCallback() {

            @Override
            public void confirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.toggleView();
            }
        });
    }
}
