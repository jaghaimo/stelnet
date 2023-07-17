package stelnet.board.contact;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import stelnet.board.contact.fleetdata.TrackingCargoFleetData;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.UiConstants;
import uilib2.Drawable;

@RequiredArgsConstructor
public class AwaitingCollection implements Drawable {

    private final Map<MarketAPI, TrackingCargoFleetData> awaitingCollection;
    private final float width;

    @Override
    public UIComponentAPI draw(final TooltipMakerAPI tooltip) {
        final float marketWidth = width / 3;
        tooltip.addSectionHeading(L10n.contacts("AWAITING_PICKUP"), Alignment.MID, UiConstants.DEFAULT_SPACER);
        tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
        tooltip.beginGridFlipped(width, 1, Misc.getTextColor(), marketWidth, UiConstants.DEFAULT_SPACER);
        addMarkets(tooltip);
        tooltip.addGrid(0);
        return tooltip.addSpacer(UiConstants.DEFAULT_SPACER);
    }

    private void addMarkets(final TooltipMakerAPI tooltip) {
        int y = 0;
        for (final MarketAPI market : awaitingCollection.keySet()) {
            y = addMarket(tooltip, market, y);
        }
    }

    private int addMarket(final TooltipMakerAPI tooltip, final MarketAPI market, int y) {
        y = addCargoStacks(tooltip, market, y);
        y = addFleetMembers(tooltip, market, y);
        return y;
    }

    private int addCargoStacks(final TooltipMakerAPI tooltip, final MarketAPI market, int y) {
        final TrackingCargoFleetData cargoFleetData = awaitingCollection.get(market);
        for (final CargoStackAPI stack : cargoFleetData.getCargoStacks()) {
            tooltip.addToGrid(
                0,
                y++,
                stack.getDisplayName(),
                StelnetHelper.getMarketWithFactionName(market),
                market.getTextColorForFactionOrPlanet()
            );
        }
        return y;
    }

    private int addFleetMembers(final TooltipMakerAPI tooltip, final MarketAPI market, int y) {
        final TrackingCargoFleetData cargoFleetData = awaitingCollection.get(market);
        for (final FleetMemberAPI member : cargoFleetData.getFleetMembers()) {
            tooltip.addToGrid(
                0,
                y++,
                member.getShipName() + ", " + member.getHullSpec().getNameWithDesignationWithDashClass(),
                StelnetHelper.getMarketWithFactionName(market),
                market.getTextColorForFactionOrPlanet()
            );
        }
        return y;
    }
}
