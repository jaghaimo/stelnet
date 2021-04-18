package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayPaneButton extends ToggleButton {

    public DisplayPaneButton() {
        super(new Size(180, 24), "Display Ships", "Display Items", true, Misc.getButtonTextColor(),
                Misc.getButtonTextColor(), true);
        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.togglePane();
            }

            @Override
            public void cancel() {
            }

            @Override
            public void prompt(TooltipMakerAPI tooltipMaker) {
            }
        });
    }
}
