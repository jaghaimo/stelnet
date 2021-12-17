package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Map;
import stelnet.util.L10n;
import stelnet.util.StringUtils;
import uilib.CustomPanel;
import uilib.Renderable;
import uilib.UiConstants;

public class ContactsPanel extends CustomPanel {

    private final float width;
    private final float marketWidth = 200;
    private final Map<MarketAPI, TrackingCargoFleetData> awaitingCollection;

    public ContactsPanel(
        float width,
        Renderable renderable,
        Map<MarketAPI, TrackingCargoFleetData> awaitingCollection
    ) {
        super(renderable);
        this.awaitingCollection = awaitingCollection;
        this.width = width;
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (!awaitingCollection.isEmpty()) {
            addAwaitingCollection(tooltip);
        }
        tooltip.addSectionHeading(L10n.get(ContactsL10n.CONTACT_LIST), Alignment.MID, UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        super.render(tooltip);
    }

    private void addAwaitingCollection(TooltipMakerAPI tooltip) {
        tooltip.addSectionHeading(L10n.get(ContactsL10n.AWAITING_PICKUP), Alignment.MID, UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.beginGridFlipped(width, 1, Misc.getTextColor(), marketWidth, UiConstants.DEFAULT_SPACER);
        addMarkets(tooltip);
        tooltip.addGrid(0);
        tooltip.addSpacer(1.5f * UiConstants.DEFAULT_SPACER);
    }

    private void addMarkets(TooltipMakerAPI tooltip) {
        int y = 0;
        for (MarketAPI market : awaitingCollection.keySet()) {
            y = addMarket(tooltip, market, y);
        }
    }

    private int addMarket(TooltipMakerAPI tooltip, MarketAPI market, int y) {
        TrackingCargoFleetData cargoFleetData = awaitingCollection.get(market);
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
