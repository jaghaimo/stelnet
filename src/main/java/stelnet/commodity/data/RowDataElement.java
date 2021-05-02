package stelnet.commodity.data;

import java.awt.Color;
import java.util.ArrayList;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

public class RowDataElement {
    ArrayList<Object> elements = new ArrayList<>();

    public RowDataElement addRowNumber(Integer i) {
        return addCustomRow(Misc.getGrayColor(), i + ".");
    }

    public RowDataElement addDGSCreditsRow(float value) {
        return addCustomRow(Misc.getHighlightColor(), Misc.getDGSCredits(value));
    }

    public RowDataElement addDGSRow(int value) {
        return addCustomRow(Misc.getHighlightColor(), Misc.getWithDGS(value));
    }

    public RowDataElement addExcessRow(int value) {
        return addCustomRow(getExcessColor(value), getExcessValue(value));
    }

    public RowDataElement addCustomRow(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
        return this;
    }

    public Object[] buildObjectArray() {
        return elements.toArray();
    }

    private static String getExcessValue(int excess) {
        if (excess > 0) {
            return Misc.getWithDGS(excess);
        }
        if (excess < 0) {
            return Misc.getWithDGS(-excess);
        }
        return "---";
    }

    private static Color getExcessColor(int excess) {
        if (excess > 0) {
            return Misc.getPositiveHighlightColor();
        }
        if (excess < 0) {
            return Misc.getNegativeHighlightColor();
        }
        return Misc.getGrayColor();
    }
}
