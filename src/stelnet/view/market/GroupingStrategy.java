package stelnet.view.market;

import java.util.List;

/**
 * Provides market content using various grouping logic.
 */
public enum GroupingStrategy implements DisplayStrategy {
    PER_LOCATION("PerLocation") {
        @Override
        public GroupingStrategy getNext() {
            return UNIFIED;
        }

        @Override
        public List<LocationContent> getData(FilterManager filterManager) {
            return new PerMarketStrategy().getData(filterManager);
        }
    },
    UNIFIED("Unified") {
        @Override
        public GroupingStrategy getNext() {
            return PER_LOCATION;
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

    public GroupingStrategy getNext() {
        return null;
    }

    public List<LocationContent> getData(FilterManager filterManager) {
        return null;
    }
}
