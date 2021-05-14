
package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.L10n;

public class Ships extends AbstractRenderable {

    private final List<FleetMemberAPI> ships;
    private String emptyDescription;

    public Ships(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        this.ships = ships;
        setEmptyDescription(emptyDescription);
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

    public void setEmptyDescription(String emptyDescription) {
        this.emptyDescription = L10n.get(emptyDescription);
    }
}
