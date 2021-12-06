package stelnet.board.commodity.market.table;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.ArrayList;
import uilib.TableContentRow;

public class TableRow implements TableContentRow {

    ArrayList<Object> elements = new ArrayList<>();

    public void addRowNumberCell(Integer i) {
        addCell(Misc.getGrayColor(), i + ".");
    }

    public void addDGSCreditsCell(float value) {
        addCell(Misc.getTextColor(), Misc.getDGSCredits(value));
    }

    public void addDGSCell(int value) {
        addCell(Misc.getTextColor(), Misc.getWithDGS(value));
    }

    public void addExcessDemandCell(int value) {
        addCell(getExcessDemandColor(value), getExcessDemandValue(value));
    }

    protected void addCell(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
    }

    public Object[] buildObjectArray() {
        return elements.toArray();
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
