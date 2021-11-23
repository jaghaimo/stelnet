package stelnet.widget.viewer;

import stelnet.util.L10n;
import stelnet.widget.WidgetL10n;
import uilib.Renderable;
import uilib.ShowCargo;
import uilib.ShowShips;
import uilib.property.Size;

/**
 * Controls type of renderer for externally provided content.
 */
public enum ContentRenderer {
    ITEMS {
        @Override
        public Renderable getStorageRenderer(LocationContent data) {
            return new ShowCargo(data.getItems(), L10n.get(WidgetL10n.VIEWER_NO_ITEMS), new Size(0, 0));
        }
    },
    SHIPS {
        @Override
        public Renderable getStorageRenderer(LocationContent data) {
            return new ShowShips(data.getShips(), L10n.get(WidgetL10n.VIEWER_NO_SHIPS), new Size(0, 0));
        }
    };

    public Renderable getStorageRenderer(LocationContent data) {
        return null;
    }
}
