package stelnet.storage;

import java.util.List;

import stelnet.storage.data.DataProvider;
import stelnet.storage.data.PerLocationProvider;
import stelnet.storage.data.StorageData;
import stelnet.storage.data.UnifiedProvider;
import stelnet.storage.view.DisplayViewButton;

public enum StorageView implements DataProvider {

    PER_LOCATION("PerLocation") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(UNIFIED);
        }

        @Override
        public List<StorageData> getData(FilterManager filterManager) {
            return new PerLocationProvider().getData(filterManager);
        }
    },
    UNIFIED("Unified") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(PER_LOCATION);
        }

        @Override
        public List<StorageData> getData(FilterManager filterManager) {
            return new UnifiedProvider().getData(filterManager);
        }
    };

    private final String title;

    private StorageView(String title) {
        this.title = title;
    }

    public DisplayViewButton getNextButton() {
        return null;
    }

    public List<StorageData> getData(FilterManager filterManager) {
        return null;
    }

    public String getTitle() {
        return title;
    }
}
