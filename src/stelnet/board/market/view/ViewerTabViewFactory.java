package stelnet.board.market.view;

import stelnet.board.market.ViewerState;
import stelnet.board.storage.view.StorageTabViewFactory;
import stelnet.view.market.ContentRenderer;
import uilib.TabButton;

public class ViewerTabViewFactory extends StorageTabViewFactory {

    public ViewerTabViewFactory(ViewerState viewerState) {
        super(viewerState.getFilteringButtons(), viewerState.getActiveRenderer(), viewerState.getMarketProvider());
    }

    @Override
    protected TabButton getTabButton(ContentRenderer currentTab, int keyboardShortcut) {
        return new ViewerTabButton(currentTab, isActive(currentTab), keyboardShortcut);
    }
}
