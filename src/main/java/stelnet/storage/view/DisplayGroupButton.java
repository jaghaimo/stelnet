package stelnet.storage.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayGroupButton extends ToggleButton {

    public DisplayGroupButton() {
        super(new Size(180, 24), "Group By Location", "Disable Grouping", true, Misc.getButtonTextColor(),
                Misc.getButtonTextColor(), true);
        setCallback(new Callable() {

            @Override
            public void callback() {
                StorageBoard board = StorageBoard.getInstance();
                board.toggleView();
            }
        });
    }
}
