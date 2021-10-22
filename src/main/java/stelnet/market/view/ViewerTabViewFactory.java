package stelnet.market.view;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.SubmarketDataRenderer;
import stelnet.storage.data.DataProvider;
import stelnet.storage.view.StorageTabViewFactory;
import uilib.TabButton;

public class ViewerTabViewFactory extends StorageTabViewFactory {

    public ViewerTabViewFactory(
        ButtonManager buttonManager,
        FilterManager filterManager,
        SubmarketDataRenderer activeTab,
        DataProvider activeView
    ) {
        super(buttonManager, filterManager, activeTab, activeView);
    }

    @Override
    protected TabButton getTabButton(SubmarketDataRenderer currentTab, int keyboardShortcut) {
        return new ViewerTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }
}
