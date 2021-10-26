package stelnet.view.market;

import java.util.List;
import stelnet.board.storage.view.DisplayViewButton;

/**
 * Provides market content using various grouping logic.
 */
public enum GroupingStrategy implements DisplayStrategy {
    PER_LOCATION("PerLocation") {
        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(UNIFIED);
        }

        @Override
        public List<LocationContent> getData(FilterManager filterManager) {
            return new PerMarketStrategy().getData(filterManager);
        }
    },
    UNIFIED("Unified") {
        @Override
        public DisplayViewButton getNextButton() {
            return new DisplayViewButton(PER_LOCATION);
        }

        @Override
        public List<LocationContent> getData(FilterManager filterManager) {
            return new UnifiedStrategy().getData(filterManager);
        }
    };

    public final String id;

    private GroupingStrategy(String id) {
        this.id = id;
    }

    public DisplayViewButton getNextButton() {
        return null;
    }

    public List<LocationContent> getData(FilterManager filterManager) {
        return null;
    }
}
