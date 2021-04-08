package stelnet.market.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class LineElement extends BoardElement {

    public LineElement(float width, float height) {
        super(width, height);
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        inner.addButton("", "", width, height, 0f).setEnabled(false);
    }
}
