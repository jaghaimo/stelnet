package stelnet.storage.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.L10n;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageBoard;
import stelnet.ui.AreaCheckbox;
import stelnet.ui.Size;

public abstract class FilteringButton extends AreaCheckbox {

    public FilteringButton(String translationId) {
        super(new Size(180, 24), L10n.get(translationId), true, true, Misc.getHighlightColor(), Misc.getGrayColor());
    }

    protected FilterManager getFilterManager() {
        StorageBoard board = StorageBoard.getInstance();
        return board.getFilterManager();
    }
}
