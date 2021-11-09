package uilib;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import uilib.property.Size;

public class ShowShips extends RenderableComponent {

    private final List<FleetMemberAPI> ships;
    private final String emptyDescription;

    public ShowShips(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        this.ships = ships;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (ships.isEmpty()) {
            tooltip.addPara(emptyDescription, 10f);
        } else {
            tooltip.showShips(ships, ships.size(), true, 5f);
        }
    }
}
