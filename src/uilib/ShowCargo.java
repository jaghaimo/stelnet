package uilib;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class ShowCargo extends RenderableComponent {

    private final CargoAPI cargo;
    private final String optionalTitle;
    private final String emptyDescription;
    private Color titleColor = Misc.getBasePlayerColor();

    public ShowCargo(CargoAPI cargo, String emptyDescription, Size size) {
        this(cargo, null, emptyDescription, size);
    }

    public ShowCargo(CargoAPI cargo, String optionalTitle, String emptyDescription, Size size) {
        this.cargo = cargo;
        this.optionalTitle = optionalTitle;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (cargo.isEmpty()) {
            tooltip.addPara(emptyDescription, 10);
            return;
        }
        if (optionalTitle != null) {
            addSectionTitle(tooltip, optionalTitle, titleColor);
        }
        tooltip.showCargo(cargo, cargo.getStacksCopy().size(), false, 5);
        setOffsetOfLast(tooltip, -6);
    }
}
