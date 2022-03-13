package stelnet.board.commodity.table;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import stelnet.util.StelnetHelper;
import uilib.TableContentRow;

public class MarketTableRow implements TableContentRow {

    private List<Object> elements = new LinkedList<>();

    public MarketTableRow(int i, float price, MarketAPI market, int demandOrAvailability, int excessOrDeficit) {
        String starSystemName = StelnetHelper.getStarSystemName(market.getStarSystem(), true);
        addRowNumberCell(i);
        addDGSCreditsCell(price);
        addDGSCell(demandOrAvailability);
        addExcessDemandCell(excessOrDeficit);
        addCell(StelnetHelper.getFactionColor(market.getFaction()), StelnetHelper.getMarketWithFactionName(market));
        addCell(StelnetHelper.getClaimingFactionColor(market), starSystemName);
        addCell(Misc.getTextColor(), String.format("%.1f", Misc.getDistanceToPlayerLY(market.getPrimaryEntity())));
    }

    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    private void addRowNumberCell(Integer i) {
        addCell(Misc.getGrayColor(), i + ".");
    }

    private void addDGSCreditsCell(float value) {
        addCell(Misc.getTextColor(), Misc.getDGSCredits(value));
    }

    private void addDGSCell(int value) {
        addCell(Misc.getTextColor(), Misc.getWithDGS(value));
    }

    private void addExcessDemandCell(int value) {
        addCell(getExcessDemandColor(value), getExcessDemandValue(value));
    }

    private void addCell(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    private static String getExcessDemandValue(int excessDemand) {
        if (excessDemand > 0) {
            return Misc.getWithDGS(excessDemand);
        }
        if (excessDemand < 0) {
            return Misc.getWithDGS(-excessDemand);
        }
        return "---";
    }

    private static Color getExcessDemandColor(int excessDemand) {
        if (excessDemand > 0) {
            return Misc.getPositiveHighlightColor();
        }
        if (excessDemand < 0) {
            return Misc.getNegativeHighlightColor();
        }
        return Misc.getGrayColor();
    }
}
