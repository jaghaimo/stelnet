
package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Ships extends Renderable {

    private List<FleetMemberAPI> ships;
    private String emptyDescription;
    private float width;
    private float height;

    public Ships(List<FleetMemberAPI> ships, String emptyDescription, float width, float height) {
        this.ships = ships;
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
        if (ships.isEmpty()) {
            tooltip.addPara(emptyDescription, 10f);
        } else {
            tooltip.showShips(ships, ships.size(), false, 5f);
        }
    }
}
