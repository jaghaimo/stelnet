package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Map;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import uilib.CustomPanel;
import uilib.Renderable;
import uilib.UiConstants;

public class ContactsPanel extends CustomPanel {

    private final Map<MarketAPI, TrackingCargoFleetData> needingPickup;

    public ContactsPanel(Renderable renderable, Map<MarketAPI, TrackingCargoFleetData> needingPickup) {
        super(renderable);
        this.needingPickup = needingPickup;
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        super.render(panel, 0, 0);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (!needingPickup.isEmpty()) {
            tooltip.addSectionHeading("Ships and items pending collection", Alignment.MID, 10);
            tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
            tooltip.beginGridFlipped(500, 1, Misc.getTextColor(), 200, UiConstants.DEFAULT_SPACER);
            addMarkets(tooltip);
            tooltip.addGrid(0);
            tooltip.addSpacer(1.5f * UiConstants.DEFAULT_SPACER);
        }
        tooltip.addSectionHeading(L10n.get(ContactsL10n.CONTACT_LIST), Alignment.MID, 10);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        super.render(tooltip);
    }

    private void addMarkets(TooltipMakerAPI tooltip) {
        int y = 0;
        for (MarketAPI market : needingPickup.keySet()) {
            y = addMarket(tooltip, market, y);
        }
    }

    private int addMarket(TooltipMakerAPI tooltip, MarketAPI market, int y) {
        TrackingCargoFleetData cargoFleetData = needingPickup.get(market);
        for (CargoStackAPI stack : cargoFleetData.getCargoStacks()) {
            tooltip.addToGrid(
                0,
                y++,
                stack.getDisplayName(),
                StringUtils.getMarketAndFactionDisplayName(market),
                market.getTextColorForFactionOrPlanet()
            );
        }
        for (FleetMemberAPI member : cargoFleetData.getFleetMembers()) {
            tooltip.addToGrid(
                0,
                y++,
                member.getShipName() + ", " + member.getHullSpec().getNameWithDesignationWithDashClass(),
                StringUtils.getMarketAndFactionDisplayName(market),
                market.getTextColorForFactionOrPlanet()
            );
        }
        return y;
    }
}
