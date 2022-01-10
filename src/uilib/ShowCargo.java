package uilib;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Setter;
import stelnet.util.CargoUtils;
import uilib.property.Size;

@Setter
public class ShowCargo extends RenderableComponent {

    private final CargoAPI cargo;
    private final String optionalTitle;
    private final String emptyDescription;
    private Color titleColor = Misc.getTextColor();

    public ShowCargo(CargoAPI cargo, String emptyDescription, Size size) {
        this(cargo, null, emptyDescription, size);
    }

    public ShowCargo(CargoAPI cargo, String optionalTitle, String emptyDescription, Size size) {
        this.cargo = cargo;
        this.optionalTitle = optionalTitle;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    public ShowCargo(List<CargoStackAPI> stacks, String optionalTitle, String emptyDescription, Size size) {
        this(CargoUtils.makeCargoFromStacks(stacks), optionalTitle, emptyDescription, size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (cargo.isEmpty()) {
            tooltip.addPara(emptyDescription, UiConstants.DEFAULT_SPACER);
            return;
        }
        if (optionalTitle != null) {
            addSectionTitle(tooltip, optionalTitle, titleColor, getSize().getWidth() - 12);
        }
        tooltip.showCargo(cargo, cargo.getStacksCopy().size(), false, 5);
        setOffsetOfLast(tooltip, -6);
    }
}
