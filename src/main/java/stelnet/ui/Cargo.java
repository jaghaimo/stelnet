
package stelnet.ui;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Cargo extends Renderable {

    private CargoAPI cargo;
    private String emptyDescription;
    private float width;
    private float height;

    public Cargo(CargoAPI cargo, String emptyDescription, float width, float height) {
        this.cargo = cargo;
        this.emptyDescription = emptyDescription;
        this.width = width;
        this.height = height;
    }

    @Override
    public Size getSize() {
        return new Size(width, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (cargo.isEmpty()) {
            tooltip.addPara(emptyDescription, 10f);
        } else {
            tooltip.showCargo(cargo, cargo.getStacksCopy().size(), false, 5f);
        }
    }
}
