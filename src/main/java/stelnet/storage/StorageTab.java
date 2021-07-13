package stelnet.storage;

import stelnet.L10n;
import stelnet.storage.data.StorageData;
import stelnet.ui.AbstractRenderable;
import stelnet.ui.Cargo;
import stelnet.ui.Ships;
import stelnet.ui.property.Size;

public enum StorageTab {

    ITEMS("Items") {

        @Override
        public AbstractRenderable getStorageRenderer(StorageData data) {
            return new Cargo(data.getItems(), L10n.get("storageNoItems"), new Size(0, 0));
        }
    },
    SHIPS("Ships") {

        @Override
        public AbstractRenderable getStorageRenderer(StorageData data) {
            return new Ships(data.getShips(), L10n.get("storageNoShips"), new Size(0, 0));
        }
    };

    public final String title;

    private StorageTab(String title) {
        this.title = title;

    }

    public AbstractRenderable getStorageRenderer(StorageData data) {
        return null;
    }
}
