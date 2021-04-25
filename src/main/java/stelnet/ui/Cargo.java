
package stelnet.ui;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Cargo extends Renderable {

    private CargoAPI cargo;
    private String emptyDescription;

    public Cargo(CargoAPI cargo, String emptyDescription, Size size) {
        this.cargo = cargo;
        this.emptyDescription = emptyDescription;
        setSize(size);
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
