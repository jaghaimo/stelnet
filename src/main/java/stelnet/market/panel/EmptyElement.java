
package stelnet.market.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class EmptyElement extends BoardElement {

    public EmptyElement(float width, float height) {
        super(width, height);
    }

    @Override
    public void render(TooltipMakerAPI inner) {
    }
}
