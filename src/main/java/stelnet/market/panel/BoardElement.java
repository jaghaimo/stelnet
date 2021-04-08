package stelnet.market.panel;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class BoardElement {

    protected float width;
    protected float height;

    public BoardElement(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public abstract void render(TooltipMakerAPI inner);

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
