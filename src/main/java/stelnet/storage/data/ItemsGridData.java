package stelnet.storage.data;

import stelnet.L10n;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Cargo;
import stelnet.ui.Size;

public class ItemsGridData extends SharedData {

    public ItemsGridData(ButtonManager buttonManager, FilterManager filterManager) {
        super(buttonManager, filterManager);
    }

    protected ItemsGridData(ButtonManager buttonManager, FilterManager filterManager, DataProvider dataProvider) {
        super(buttonManager, filterManager);
        this.dataProvider = dataProvider;
    }

    @Override
    public SharedData getNext() {
        return new ShipsGridData(buttonManager, filterManager, dataProvider);
    }

    @Override
    protected AbstractRenderable[] getButtons() {
        return buttonManager.getItemButtons();
    }

    @Override
    protected AbstractRenderable getStorageContent(StorageData data) {
        return new Cargo(data.getItems(), L10n.get("storageNoItems"), new Size(0, 0));
    }
}
