package uilib;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import stelnet.util.CargoUtils;
import uilib.property.Size;

public class ShowCargo extends RenderableComponent {

    private final CargoAPI cargo;
    private final String emptyDescription;

    public ShowCargo(List<CargoStackAPI> cargoStacks, String emptyDescription, Size size) {
        this(CargoUtils.makeCargoFromStacks(cargoStacks), emptyDescription, size);
    }

    public ShowCargo(CargoAPI cargo, String emptyDescription, Size size) {
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
