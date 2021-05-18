package stelnet.storage.data;

import stelnet.L10n;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Ships;
import stelnet.ui.Size;

public class ShipsGridData extends SharedData {

    public ShipsGridData(ButtonManager buttonManager, FilterManager filterManager, DataProvider dataProvider) {
        super(buttonManager, filterManager);
        this.dataProvider = dataProvider;
    }

    @Override
    public SharedData getNext() {
        return new ItemsGridData(buttonManager, filterManager, dataProvider);
    }

    @Override
    protected AbstractRenderable[] getButtons() {
        return buttonManager.getShipButtons();
    }

    @Override
    protected AbstractRenderable getStorageContent(StorageData data) {
        return new Ships(data.getShips(), L10n.get("storageNoShips"), new Size(0, 0));
    }
}
