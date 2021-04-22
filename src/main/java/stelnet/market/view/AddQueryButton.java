package stelnet.market.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.ui.Button;
import stelnet.ui.Size;

public class AddQueryButton extends Button {

    public AddQueryButton() {
        super(new Size(160, 24), "Add New Query", true, Misc.getPositiveHighlightColor());
    }
}
