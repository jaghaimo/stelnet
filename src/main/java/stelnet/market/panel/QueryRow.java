package stelnet.market.panel;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.util.Misc;

import stelnet.market.IntelQuery;

public class QueryRow extends BoardRow {

    public final static String BUTTON_DELETE = "HYPERNET_DELETE";
    public final static String BUTTON_DISABLE = "HYPERNET_DISABLE";
    public final static String BUTTON_ENABLE = "HYPERNET_ENABLE";

    private int queryNumber;
    private IntelQuery query;

    public QueryRow(CustomPanelAPI panel, float width, int queryNumber, IntelQuery query) {
        super(panel, width);
        this.queryNumber = queryNumber;
        this.query = query;
    }

    @Override
    public float getHeight() {
        return super.getHeight() + 10f;
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ParaElement(0, 20f, query));
        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        String queryIdentifier = "#" + String.valueOf(queryNumber);
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ButtonElement(100f, 20f, "Delete", BUTTON_DELETE + queryIdentifier, true,
                Misc.getNegativeHighlightColor()));
        elements.add(new EmptyElement(0f, 20f));
        if (query.isEnabled()) {
            elements.add(new ButtonElement(60f, 20f, "On", BUTTON_DISABLE + queryIdentifier, true,
                    Misc.getButtonTextColor()));
        } else {
            elements.add(
                    new ButtonElement(60f, 20f, "Off", BUTTON_ENABLE + queryIdentifier, true, Misc.getGrayColor()));
        }
        elements.add(new ParaElement(80f, 20f, query.isActive(), query.getResultCount()));
        return elements;
    }

}
