package stelnet.storage;

import java.util.List;
import stelnet.storage.data.DisplayStrategy;
import stelnet.storage.data.PerMarketStrategy;
import stelnet.storage.data.SubmarketData;
import stelnet.storage.data.UnifiedStrategy;
import stelnet.storage.view.DisplayViewButton;

/**
 * Provides submarket data using various grouping logic.
 */
public enum SubmarketDataProvider implements DisplayStrategy {
    PER_LOCATION("PerLocation") {
        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(UNIFIED);
        }

        @Override
        public List<SubmarketData> getData(FilterManager filterManager) {
            return new PerMarketStrategy().getData(filterManager);
        }
    },
    UNIFIED("Unified") {
        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(PER_LOCATION);
        }

        @Override
        public List<SubmarketData> getData(FilterManager filterManager) {
            return new UnifiedStrategy().getData(filterManager);
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
