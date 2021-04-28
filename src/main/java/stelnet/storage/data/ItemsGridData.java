package stelnet.storage.data;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.ui.Cargo;
import stelnet.ui.Renderable;
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
    protected Renderable[] getButtons() {
        return buttonManager.getItemButtons();
    }

    @Override
    protected Renderable getStorageContent(StorageData data) {
        return new Cargo(data.getItems(), "There are no matching items to display", new Size(0, 0));
    }
}
