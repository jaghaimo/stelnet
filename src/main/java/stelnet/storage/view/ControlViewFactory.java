package stelnet.storage.view;

import java.util.Arrays;

import stelnet.storage.ButtonManager;
import stelnet.storage.StorageBoard;
import stelnet.ui.Renderable;
import stelnet.ui.Stack;

public class ControlViewFactory {

    public Renderable get(StorageBoard.Pane pane, ButtonManager buttonManager) {
        Renderable[] common = buttonManager.getCommonButtons();
        Renderable[] buttons = getButtons(pane, buttonManager);
        Renderable[] all = Arrays.copyOf(common, common.length + buttons.length);
        System.arraycopy(buttons, 0, all, common.length, buttons.length);
        return new Stack(all);
    }

    private Renderable[] getButtons(StorageBoard.Pane pane, ButtonManager buttonManager) {
        if (pane.equals(StorageBoard.Pane.Cargo)) {
            return buttonManager.getCargoButtons();
        }
        return buttonManager.getFleetButtons();
    }
}
