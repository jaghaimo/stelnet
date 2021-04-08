package stelnet.storage.panel;

import java.util.Arrays;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stelnet.storage.StorageBoard;
import stelnet.storage.button.ButtonManager;
import stelnet.storage.button.DisplayMode;

public class CargoControl extends BoardElement {

    public CargoControl(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        renderFilters();
    }

    private void renderFilters() {
        float currentHeight = 0;
        ButtonManager buttonManager = board.getButtonManager();
        currentHeight = renderFilters(Arrays.asList(buttonManager.getDisplayModeButton(), new DisplayMode(false)),
                currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoTypeButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoWeaponButtons(), currentHeight);
        currentHeight += 20f;
        currentHeight = renderFilters(buttonManager.getCargoFighterWingsButtons(), currentHeight);
    }
}
