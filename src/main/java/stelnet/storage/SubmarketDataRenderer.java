package stelnet.storage;

import stelnet.storage.data.SubmarketData;
import stelnet.util.L10n;
import uilib.Cargo;
import uilib.Renderable;
import uilib.Ships;
import uilib.property.Size;

/**
 * Controls type of renderer for externally provided content.
 */
public enum SubmarketDataRenderer {
    ITEMS("Items") {
        @Override
        public Renderable getStorageRenderer(SubmarketData data) {
            return new Cargo(
                data.getItems(),
                L10n.get("storageNoItems"),
                new Size(0, 0)
            );
        }
    },
    SHIPS("Ships") {
        @Override
        public Renderable getStorageRenderer(SubmarketData data) {
            return new Ships(
                data.getShips(),
                L10n.get("storageNoShips"),
                new Size(0, 0)
            );
        }
    };

    public final String title;

    private SubmarketDataRenderer(String title) {
        this.title = title;
    }

    public Renderable getStorageRenderer(SubmarketData data) {
        return null;
    }
}
