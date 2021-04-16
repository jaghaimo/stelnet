package stelnet.storage.data;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.Renderable;
import stelnet.ui.Ships;

public class ShipsGridData extends SharedData {

    public ShipsGridData(ButtonManager buttonManager, FilterManager filterManager) {
        super(buttonManager, filterManager);
    }

    public ShipsGridData(ButtonManager buttonManager, FilterManager filterManager, DataProvider dataProvider) {
        super(buttonManager, filterManager);
        this.dataProvider = dataProvider;
    }

    @Override
    public SharedData getNext() {
        return new ItemsGridData(buttonManager, filterManager, dataProvider);
    }

    @Override
    protected Renderable[] getButtons() {
        return buttonManager.getShipButtons();
    }

    @Override
    protected Renderable getStorageContent(StorageData data) {
        return new Ships(data.getShips(), "There are no matching ships to display", 0, 0);
    }
}
