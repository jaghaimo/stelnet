package stelnet.ui;

import java.awt.Color;
import java.util.ArrayList;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

public class RowDataElement {
    ArrayList<Object> elements = new ArrayList<>();

    public void addRowNumber(Integer i) {
        addCustomRow(Misc.getGrayColor(), i + ".");
    }

    public void addDGSCreditsRow(float value) {
        addCustomRow(Misc.getHighlightColor(), Misc.getDGSCredits(value));
    }

    public void addDGSRow(int value) {
        addCustomRow(Misc.getHighlightColor(), Misc.getWithDGS(value));
    }

    public void addExcessRow(int value) {
        addCustomRow(getExcessColor(value), getExcessValue(value));
    }

    public void addCustomRow(Color color, Object element) {
        elements.add(Alignment.MID);
        elements.add(color);
        elements.add(element.toString());
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
