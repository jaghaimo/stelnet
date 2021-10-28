package stelnet.widget.viewer;

import java.util.List;

public interface DisplayStrategy {
    public List<LocationContent> getData(FilteringButtons filteringButtons);
}
