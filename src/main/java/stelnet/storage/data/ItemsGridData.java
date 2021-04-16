package stelnet.storage.data;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.Renderable;

public class ItemsGridData extends SharedData {

    public ItemsGridData(ButtonManager buttonManager, FilterManager filterManager) {
        super(buttonManager, filterManager);
    }

    @Override
    public Renderable getTopLeft() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SharedData getNext() {
        return new ShipsGridData(buttonManager, filterManager);
    }

    @Override
    protected Renderable[] getButtons() {
        return buttonManager.getItemButtons();
    }
}
