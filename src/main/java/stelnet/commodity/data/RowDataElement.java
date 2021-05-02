package stelnet.commodity.data;

import java.awt.Color;
import java.util.ArrayList;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

import stelnet.ui.TableContentRow;

public class RowDataElement implements TableContentRow {

    ArrayList<Object> elements = new ArrayList<>();

    public void addRowNumber(Integer i) {
        addRow(Misc.getGrayColor(), i + ".");
    }

    public void addDGSCreditsRow(float value) {
        addRow(Misc.getHighlightColor(), Misc.getDGSCredits(value));
    }

    public void addDGSRow(int value) {
        addRow(Misc.getHighlightColor(), Misc.getWithDGS(value));
    }

    public void addExcessRow(int value) {
        addRow(getExcessColor(value), getExcessValue(value));
    }

    public void addRow(Color color, Object element) {
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
