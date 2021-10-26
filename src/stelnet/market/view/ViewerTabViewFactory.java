package stelnet.market.view;

import stelnet.market.ViewerState;
import stelnet.storage.SubmarketDataRenderer;
import stelnet.storage.view.StorageTabViewFactory;
import uilib.TabButton;

public class ViewerTabViewFactory extends StorageTabViewFactory {

    public ViewerTabViewFactory(ViewerState viewerState) {
        super(
            viewerState.getButtonManager(),
            viewerState.getFilterManager(),
            viewerState.getActiveTab(),
            viewerState.getMarketProvider()
        );
    }

    @Override
    protected TabButton getTabButton(SubmarketDataRenderer currentTab, int keyboardShortcut) {
        return new ViewerTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }
}
