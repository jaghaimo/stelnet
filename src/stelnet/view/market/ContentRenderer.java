package stelnet.view.market;

import stelnet.util.L10n;
import uilib.Cargo;
import uilib.Renderable;
import uilib.Ships;
import uilib.property.Size;

/**
 * Controls type of renderer for externally provided content.
 */
public enum ContentRenderer {
    ITEMS("Items") {
        @Override
        public Renderable getStorageRenderer(LocationContent data) {
            return new Cargo(data.getItems(), L10n.get("viewNoItems"), new Size(0, 0));
        }
    },
    SHIPS("Ships") {
        @Override
        public Renderable getStorageRenderer(LocationContent data) {
            return new Ships(data.getShips(), L10n.get("viewNoShips"), new Size(0, 0));
        }
    };

    public final String id;

    private ContentRenderer(String id) {
        this.id = id;
    }

    public Renderable getStorageRenderer(LocationContent data) {
        return null;
    }
}
