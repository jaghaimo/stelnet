package stelnet.market.view;

import stelnet.storage.ButtonManager;
import stelnet.storage.FilterManager;
import stelnet.storage.StorageTab;
import stelnet.storage.data.DataProvider;
import stelnet.storage.view.StorageTabViewFactory;
import stelnet.ui.TabButton;

public class ViewerTabViewFactory extends StorageTabViewFactory {

    public ViewerTabViewFactory(
            ButtonManager buttonManager,
            FilterManager filterManager,
            StorageTab activeTab,
            DataProvider activeView
    ) {
        super(buttonManager, filterManager, activeTab, activeView);
    }

    @Override
    protected TabButton getTabButton(StorageTab currentTab, int keyboardShortcut) {
        return new ViewerTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }
}
