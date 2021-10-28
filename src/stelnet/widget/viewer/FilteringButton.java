package stelnet.widget.viewer;

import com.fs.starfarer.api.util.Misc;
import stelnet.util.L10n;
import uilib.AreaCheckbox;
import uilib.property.Size;

public abstract class FilteringButton extends AreaCheckbox {

    public FilteringButton(String translationId) {
        super(new Size(180, 24), L10n.get(translationId), true, true, Misc.getHighlightColor(), Misc.getGrayColor());
    }
}
