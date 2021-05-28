package stelnet.storage;

import java.util.List;

import stelnet.storage.data.PerLocationProvider;
import stelnet.storage.data.StorageData;
import stelnet.storage.data.UnifiedProvider;
import stelnet.storage.view.DisplayViewButton;

public enum StorageView {

    PER_LOCATION("PerLocation") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(UNIFIED);
        }

        @Override
        public List<StorageData> getStorageData(FilterManager filterManager) {
            return new PerLocationProvider(filterManager).getData();
        }
    },
    UNIFIED("Unified") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(PER_LOCATION);
        }

        @Override
        public List<StorageData> getStorageData(FilterManager filterManager) {
            return new UnifiedProvider(filterManager).getData();
        }
    };

    public final String title;

    private StorageView(String title) {
        this.title = title;
    }

    public DisplayViewButton getNextButton() {
        return null;
    }

    public List<StorageData> getStorageData(FilterManager filterManager) {
        return null;
    }
}
