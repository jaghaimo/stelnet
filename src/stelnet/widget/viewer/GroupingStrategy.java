package stelnet.widget.viewer;

import java.util.List;

/**
 * Provides market content using various grouping logic.
 */
public enum GroupingStrategy implements DisplayStrategy {
    PER_LOCATION {
        @Override
        public GroupingStrategy getNext() {
            return UNIFIED;
        }

        @Override
        public List<LocationContent> getData(final ButtonManager filteringButtons) {
            return new PerMarketStrategy().getData(filteringButtons);
        }
    },
    UNIFIED {
        @Override
        public GroupingStrategy getNext() {
            return PER_LOCATION;
        }

        @Override
        public List<LocationContent> getData(final ButtonManager filteringButtons) {
            return new UnifiedStrategy().getData(filteringButtons);
        }
    };

    public GroupingStrategy getNext() {
        return null;
    }

    public List<LocationContent> getData(final ButtonManager filterButtons) {
        return null;
    }
}
