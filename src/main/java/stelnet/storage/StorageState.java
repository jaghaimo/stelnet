package stelnet.storage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private SubmarketDataRenderer activeTab = SubmarketDataRenderer.ITEMS;
    private SubmarketDataProvider activeView = SubmarketDataProvider.UNIFIED;
}
