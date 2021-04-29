package stelnet.storage.view;

import com.fs.starfarer.api.util.Misc;

import stelnet.storage.FilterManager;
import stelnet.storage.StorageBoard;
import stelnet.ui.AreaCheckbox;
import stelnet.ui.Location;
import stelnet.ui.Size;

public abstract class FilteringButton extends AreaCheckbox {

    public FilteringButton(String name) {
        super(new Size(180, 24), name, true, true, Misc.getHighlightColor(), Misc.getGrayColor());
        setLocation(Location.TOP_RIGHT);
    }

    protected FilterManager getFilterManager() {
        StorageBoard board = StorageBoard.getInstance();
        return board.getFilterManager();
    }
}
