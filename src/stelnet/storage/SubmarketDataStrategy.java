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
public enum SubmarketDataStrategy implements DisplayStrategy {
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

    public final String id;

    private SubmarketDataStrategy(String id) {
        this.id = id;
    }

    public DisplayViewButton getNextButton() {
        return null;
    }

    public List<SubmarketData> getData(FilterManager filterManager) {
        return null;
    }
}
