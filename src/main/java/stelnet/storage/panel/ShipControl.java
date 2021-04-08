package stelnet.storage.panel;

import java.util.Arrays;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stelnet.storage.StorageBoard;
import stelnet.storage.button.ButtonManager;
import stelnet.storage.button.DisplayMode;

public class ShipControl extends BoardElement {

    public ShipControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
    }

    private void renderFilters() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(Arrays.asList(buttonManager.getDisplayModeButton(), new DisplayMode(true)),
                currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getShipSizeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getShipTypeButtons(), currentHeight);
    }
}
