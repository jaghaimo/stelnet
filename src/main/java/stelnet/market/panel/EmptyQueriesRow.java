package stelnet.market.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public class EmptyQueriesRow extends BoardRow {

    public EmptyQueriesRow(CustomPanelAPI panel, float width) {
        super(panel, width);
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new ParaElement(0, 20f, true, "No intel queries present."));
        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        return Collections.emptyList();
    }
}
