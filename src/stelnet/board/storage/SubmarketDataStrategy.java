package stelnet.board.storage;

import java.util.List;
import stelnet.board.storage.data.DisplayStrategy;
import stelnet.board.storage.data.PerMarketStrategy;
import stelnet.board.storage.data.SubmarketData;
import stelnet.board.storage.data.UnifiedStrategy;
import stelnet.board.storage.view.DisplayViewButton;

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
