package uilib;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class ShowShips extends RenderableShowComponent {

    private final List<FleetMemberAPI> ships;
    private final String optionalTitle;
    private final String emptyDescription;
    private Color titleColor = Misc.getTextColor();

    public ShowShips(List<FleetMemberAPI> ships, String emptyDescription, Size size) {
        this(ships, null, emptyDescription, size);
    }

    public ShowShips(List<FleetMemberAPI> ships, String optionalTitle, String emptyDescription, Size size) {
        super(ships.size());
        this.ships = ships;
        this.optionalTitle = optionalTitle;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (optionalTitle != null) {
            addSectionTitle(tooltip, optionalTitle, titleColor, getSize().getWidth() - UiConstants.DEFAULT_SPACER);
        }
        if (ships.isEmpty()) {
            tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
            tooltip.addPara(emptyDescription, -1);
            return;
        }
        tooltip.showShips(ships, getMaxElements(), true, 5f);
        setOffsetOfLast(tooltip, -6);
    }
}
