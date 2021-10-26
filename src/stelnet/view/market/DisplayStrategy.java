package stelnet.view.market;

import java.util.List;

public interface DisplayStrategy {
    public List<LocationContent> getData(FilterManager filterManager);
}
