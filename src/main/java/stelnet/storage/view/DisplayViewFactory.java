package stelnet.storage.view;

import stelnet.storage.FilterManager;
import stelnet.storage.StorageBoard;
import stelnet.ui.Renderable;
import stelnet.ui.Size;

public class DisplayViewFactory {

    public Renderable get(Size size, StorageBoard.Pane pane, StorageBoard.View view, FilterManager filterManager) {
        DisplayPane displayPane = getDisplayPane(pane);
        DisplayView displayView = getDisplayView(view);
        return null;
    }

    private DisplayPane getDisplayPane(StorageBoard.Pane pane) {
        if (pane.equals(StorageBoard.Pane.Cargo)) {
            return new CargoPane();
        }
        return new ShipPane();
    }

    private DisplayView getDisplayView(StorageBoard.View view) {
        if (view.equals(StorageBoard.View.Unified)) {
            return new UnifiedView();
        }
        return new PerLocationView();
    }
}
