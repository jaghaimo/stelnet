package stelnet.storage.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayPaneButton extends ToggleButton {

    public DisplayPaneButton() {
        super(new Size(180, 24), "Display Cargo", "Display Ships", true, Misc.getButtonTextColor(),
                Misc.getButtonTextColor(), true);
        setCallback(new Callable() {

            @Override
            public void callback() {
                StorageBoard board = StorageBoard.getInstance();
                board.togglePane();
            }
        });
    }
}
