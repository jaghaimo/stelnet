package stelnet.storage.button;

import java.awt.Color;

import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;

public class Button implements ButtonHandler {

    private String title;
    private boolean isEnabled;
    private boolean isStateOn;
    private Object filter;

    private Button() {
        isEnabled = true;
        isStateOn = true;
    }

    public Button(String title) {
        this();
        this.title = title;
    }

    public Button(String title, boolean isStateOn) {
        this(title);
        this.isStateOn = isStateOn;
    }

    public Button(String title, Object filter) {
        this(title);
        this.filter = filter;
    }

    public Color getColor() {
        if (isStateOn && isEnabled) {
            return Misc.getHighlightColor();
        }
        return Misc.getGrayColor();
    }

    public Object getFilter() {
        if (isStateOn) {
            return null;
        }
        return filter;
    }

    public String getTitle() {
        return title + getStateString();
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isStateOn() {
        return isStateOn;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public void handle(StorageBoard board, IntelUIAPI ui) {
        if (!isEnabled) {
            return;
        }
        toggle();
        ui.updateUIForItem(board);
    }

    protected void toggle() {
        isStateOn = !isStateOn;
    }

    protected String getStateString() {
        if (filter == null) {
            return "";
        }
        return isStateOn ? ": On" : ": Off";
    }
}
