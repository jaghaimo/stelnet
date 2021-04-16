package stelnet.storage.data;

import stelnet.storage.FilterManager;
import stelnet.storage.ButtonManager;
import stelnet.ui.Renderable;

public class ShipsGridData extends SharedData {

    public ShipsGridData(ButtonManager buttonManager, FilterManager filterManager) {
        super(buttonManager, filterManager);
    }

    @Override
    public Renderable getTopLeft() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SharedData getNext() {
        return new ItemsGridData(buttonManager, filterManager);
    }

    @Override
    protected Renderable[] getButtons() {
        return buttonManager.getShipButtons();
    }
}
