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
        public List<LocationContent> getData(FilteringButtons filteringButtons) {
            return new PerMarketStrategy().getData(filteringButtons);
        }
    },
    UNIFIED("Unified") {
        @Override
        public GroupingStrategy getNext() {
            return PER_LOCATION;
        }

        @Override
        public List<LocationContent> getData(FilteringButtons filteringButtons) {
            return new UnifiedStrategy().getData(filteringButtons);
        }
    };

    public final String id;

    private GroupingStrategy(String id) {
        this.id = id;
    }

    public GroupingStrategy getNext() {
        return null;
    }

    public List<LocationContent> getData(FilteringButtons filterButtons) {
        return null;
    }
}
