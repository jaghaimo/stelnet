package stelnet.storage.panel;

import java.awt.Color;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.storage.StorageBoard;
import stelnet.storage.button.Button;

public abstract class BoardElement {

    protected StorageBoard board;
    protected CustomPanelAPI panel;
    protected float width;
    protected float height;

    public BoardElement(StorageBoard board, CustomPanelAPI panel, float width, float height) {
        this.board = board;
        this.panel = panel;
        this.width = width;
        this.height = height;
    }

    public abstract void render();

    protected float renderFilters(List<Button> buttons, float currentHeight) {
        for (Button button : buttons) {
            renderButton(button, width).inTR(10f, currentHeight);
            currentHeight += 30f;
        }
        return currentHeight;
    }

    protected void renderCargo(CargoAPI cargo) {
        TooltipMakerAPI cargoView = panel.createUIElement(width, height, true);
        cargoView.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
        panel.addUIElement(cargoView).inTL(0, 0);
    }

    protected void renderShips(List<FleetMemberAPI> ships) {
        TooltipMakerAPI shipView = panel.createUIElement(width, height, true);
        shipView.showShips(ships, ships.size(), false, 5f);
        panel.addUIElement(shipView).inTL(0, 0);
    }

    private PositionAPI renderButton(Button button, float buttonWidth) {
        Color color = button.getColor();
        Color backgroundColor = Misc.scaleColor(color, 0.5f);
        TooltipMakerAPI buttonElement = panel.createUIElement(buttonWidth, 25f, false);
        buttonElement.addButton(button.getTitle(), button, color, backgroundColor, buttonWidth, 20f, 5f)
                .setEnabled(button.isEnabled());
        return panel.addUIElement(buttonElement);
    }
}
