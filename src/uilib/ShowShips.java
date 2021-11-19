package uilib;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class ShowShips extends RenderableComponent {

    private final List<FleetMemberAPI> ships;
    private final String optionalTitle;
    private final String emptyDescription;
    private Color titleColor = Misc.getButtonTextColor();

    public ShowShips(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        this(ships, null, emptyDescription, size);
    }

    public ShowShips(List<FleetMemberAPI> ships, String optionalTitle, String emptyDescription, Size size) {
        this.ships = ships;
        this.optionalTitle = optionalTitle;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (ships.isEmpty()) {
            tooltip.addPara(emptyDescription, UiConstants.DEFAULT_SHOW_SPACER);
            return;
        }
        if (optionalTitle != null) {
            addSectionTitle(tooltip, optionalTitle, titleColor);
        }
        tooltip.showShips(ships, ships.size(), true, 5f);
        setOffsetOfLast(tooltip, -6);
    }
}
