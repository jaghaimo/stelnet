package stelnet.storage.element;

import java.awt.Color;

import stelnet.storage.StorageBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayModeButton extends ToggleButton {

    public DisplayModeButton(Size size, boolean isEnabled, Color color, boolean isOn) {
        super(size, "Display Cargo", "Display Ships", isEnabled, color, color, isOn);
        setCallback(new Callable() {

            @Override
            public void callback() {
                StorageBoard board = StorageBoard.getInstance();
                board.togglePane();
            }
        });
    }
}
