package uilib;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import uilib.property.Size;

public class ShowShips extends RenderableComponent {

    private final List<FleetMemberAPI> ships;
    private final String optionalTitle;
    private final String emptyDescription;

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
            tooltip.addPara(emptyDescription, 10f);
            return;
        }
        if (optionalTitle != null) {
            tooltip.addPara(optionalTitle, Misc.getGrayColor(), 10);
        }
        tooltip.showShips(ships, ships.size(), true, 5f);
    }
}
