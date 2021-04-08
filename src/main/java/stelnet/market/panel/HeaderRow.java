package stelnet.market.panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;

public class HeaderRow extends BoardRow {

    public HeaderRow(CustomPanelAPI panel, float width) {
        super(panel, width);
    }

    @Override
    public float getHeight() {
        return super.getHeight() + 10f;
    }

    @Override
    protected List<BoardElement> getLeftElements() {
        List<BoardElement> elements = new ArrayList<>();
        elements.add(new LineElement(width - 10f, 1f));
        return elements;
    }

    @Override
    protected List<BoardElement> getRightElements() {
        return Collections.emptyList();
    }
}
