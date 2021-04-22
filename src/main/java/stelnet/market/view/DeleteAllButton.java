package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class DeleteAllButton extends Button {

    public DeleteAllButton() {
        super(new Size(120, 24), "Delete All", true, Misc.getNegativeHighlightColor());
    }
}
