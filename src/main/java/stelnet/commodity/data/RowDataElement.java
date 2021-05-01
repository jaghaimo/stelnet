package stelnet.commodity.data;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;
import java.util.ArrayList;

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
        return addCustomRow(TableCellHelper.getExcessColor(value), TableCellHelper.getExcessValue(value));
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
}
