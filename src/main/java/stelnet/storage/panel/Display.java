package stelnet.storage.panel;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.storage.StorageBoard;
import stelnet.storage.button.Button;
import stelnet.storage.button.ButtonManager;

public abstract class Display extends BoardElement {

    public Display(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        super(board, panel, width, height);
    }

    @Override
    public void render() {
        ButtonManager buttonManager = board.getButtonManager();
        Button displayMode = buttonManager.getDisplayModeButton();
        if (displayMode.isStateOn()) {
            renderForTransfer();
        } else {
            renderForLocation();
        }
    }

    protected void renderEmpty(CustomPanelAPI customPanel) {
        TooltipMakerAPI display = panel.createUIElement(width, height, true);
        renderEmpty(display);
        panel.addUIElement(display);
    }

    protected void renderEmpty(TooltipMakerAPI display) {
        String description = getEmptyDescription();
        display.addPara(description, 10f);
    }

    protected abstract void renderForTransfer();

    protected abstract void renderForLocation();

    protected abstract String getEmptyDescription();
}
