package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import stelnet.util.StelnetHelper;
import uilib.TableContentRow;

public class MarketTableRow implements TableContentRow {

    private final List<Object> elements = new LinkedList<>();

    public MarketTableRow(
        final int i,
        final float price,
        final MarketAPI market,
        final int demandOrAvailability,
        final int excessOrDeficit
    ) {
        final String starSystemName = StelnetHelper.getStarSystemName(market.getStarSystem(), true);
        addRowNumberCell(i);
        addDGSCreditsCell(price);
        addDGSCell(demandOrAvailability);
        addExcessDemandCell(excessOrDeficit);
        addCell(StelnetHelper.getFactionColor(market.getFaction()), StelnetHelper.getMarketWithFactionName(market));
        addCell(getClaimingFactionColor(market), starSystemName);
        addCell(Misc.getTextColor(), String.format("%.1f", Misc.getDistanceToPlayerLY(market.getPrimaryEntity())));
    }

    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    private void addRowNumberCell(final Integer i) {
        addCell(Misc.getGrayColor(), i + ".");
    }

    private void addDGSCreditsCell(final float value) {
        addCell(Misc.getTextColor(), Misc.getDGSCredits(value));
    }

    private void addDGSCell(final int value) {
        addCell(Misc.getTextColor(), Misc.getWithDGS(value));
    }

    private void addExcessDemandCell(final int value) {
        addCell(getExcessDemandColor(value), getExcessDemandValue(value));
    }

    private void addCell(final Color color, final Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    private Color getClaimingFactionColor(final MarketAPI market) {
        final FactionAPI faction = Misc.getClaimingFaction(market.getPrimaryEntity());
        return StelnetHelper.getFactionColor(faction);
    }

    private String getExcessDemandValue(final int excessDemand) {
        if (excessDemand > 0) {
            return Misc.getWithDGS(excessDemand);
        }
        if (excessDemand < 0) {
            return Misc.getWithDGS(-excessDemand);
        }
        return "---";
    }

    private Color getExcessDemandColor(final int excessDemand) {
        if (excessDemand > 0) {
            return Misc.getPositiveHighlightColor();
        }
        if (excessDemand < 0) {
            return Misc.getNegativeHighlightColor();
        }
        return Misc.getGrayColor();
    }
}
