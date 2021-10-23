package stelnet.market;

import lombok.Getter;
import lombok.Setter;
import stelnet.market.data.MarketProvider;
import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.SubmarketDataRenderer;

@Getter
@Setter
public class ViewerState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private SubmarketDataRenderer activeTab = SubmarketDataRenderer.ITEMS;
    private MarketProvider marketProvider = new MarketProvider(null);
}
