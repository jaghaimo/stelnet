package stelnet.storage;

import java.util.List;

import stelnet.storage.data.DataProvider;
import stelnet.storage.data.PerLocationProvider;
import stelnet.storage.data.SubmarketData;
import stelnet.storage.data.UnifiedProvider;
import stelnet.storage.view.DisplayViewButton;

/**
 * Provides submarket data using various grouping logic.
 */
public enum SubmarketDataProvider implements DataProvider {

    PER_LOCATION("PerLocation") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(UNIFIED);
        }

        @Override
        public List<SubmarketData> getData(FilterManager filterManager) {
            return new PerLocationProvider().getData(filterManager);
        }
    },
    UNIFIED("Unified") {

        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(PER_LOCATION);
        }

        @Override
        public List<SubmarketData> getData(FilterManager filterManager) {
            return new UnifiedProvider().getData(filterManager);
        }
    };

    private final String title;

    private SubmarketDataProvider(String title) {
        this.title = title;
    }

    public DisplayViewButton getNextButton() {
        return null;
    }

    public List<SubmarketData> getData(FilterManager filterManager) {
        return null;
    }

    public String getTitle() {
        return title;
    }
}
