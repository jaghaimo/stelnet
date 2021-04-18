package stelnet.storage.view;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.ui.Callable;
import stelnet.ui.Size;
import stelnet.ui.ToggleButton;

public class DisplayViewButton extends ToggleButton {

    public DisplayViewButton() {
        super(new Size(180, 24), "Group By Location", "Disable Grouping", true, Misc.getButtonTextColor(),
                Misc.getButtonTextColor(), true);
        setCallback(new Callable() {

            @Override
            public void confirm(IntelUIAPI ui) {
                StorageBoard board = StorageBoard.getInstance();
                board.toggleView();
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
