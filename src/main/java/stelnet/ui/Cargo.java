
package stelnet.ui;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.L10n;

public class Cargo extends AbstractRenderable {

    private final CargoAPI cargo;
    private String emptyDescription;

    public Cargo(CargoAPI cargo, String emptyDescription, Size size) {
        this.cargo = cargo;
        setEmptyDescription(emptyDescription);
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

    public void setEmptyDescription(String emptyDescription) {
        this.emptyDescription = L10n.get(emptyDescription);
    }
}
