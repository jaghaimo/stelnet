package uilib;

import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import java.util.List;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class ShowShips extends RenderableShowComponent {

    private final List<FleetMemberAPI> ships;
    private final String optionalTitle;
    private final String emptyDescription;
    private Color titleColor = Misc.getTextColor();

    public ShowShips(final List<FleetMemberAPI> ships, final String emptyDescription, final Size size) {
        this(ships, null, emptyDescription, size);
    }

    public ShowShips(
        final List<FleetMemberAPI> ships,
        final String optionalTitle,
        final String emptyDescription,
        final Size size
    ) {
        super(ships.size());
        this.ships = ships;
        this.optionalTitle = optionalTitle;
        this.emptyDescription = emptyDescription;
        setSize(size);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        if (optionalTitle != null) {
            final String fullTitle = String.format("%s (%d)", optionalTitle, ships.size());
            addSectionTitle(tooltip, fullTitle, titleColor, getSize().getWidth() - UiConstants.DEFAULT_SPACER);
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
