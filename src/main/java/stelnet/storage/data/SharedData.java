package stelnet.storage.data;

import stelnet.storage.FilterManager;
import stelnet.storage.ButtonManager;
import stelnet.ui.GridData;
import stelnet.ui.Renderable;

public abstract class SharedData implements GridData {

    protected ButtonManager buttonManager;
    protected FilterManager filterManager;

    public SharedData(ButtonManager buttonManager, FilterManager filterManager) {
        this.buttonManager = buttonManager;
        this.filterManager = filterManager;
    }

    public void changeDataProvider() {
        // TODO add this
    }

    @Override
    public Renderable getTopRight() {
        return null;
    }

    @Override
    public Renderable getBottomLeft() {
        return null;
    }

    @Override
    public Renderable getBottomRight() {
        return null;
    }

    public abstract SharedData getNext();

    protected abstract Renderable[] getButtons();
}
