package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class DeleteAllButton extends Button {

    public DeleteAllButton(boolean isEnabled) {
        super(new Size(120, 24), "Delete All", isEnabled, Misc.getNegativeHighlightColor());
    }
}
