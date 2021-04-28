
package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Ships extends AbstractRenderable {

    private final List<FleetMemberAPI> ships;
    private final String emptyDescription;

    public Ships(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        this.ships = ships;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (ships.isEmpty()) {
            tooltip.addPara(emptyDescription, 10f);
        } else {
            tooltip.showShips(ships, ships.size(), false, 5f);
        }
    }
}
