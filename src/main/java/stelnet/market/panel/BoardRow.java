package stelnet.market.panel;

import java.util.List;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public abstract class BoardRow {

    private static final float SPACING = 5f;

    protected CustomPanelAPI panel;
    protected float width;

    public BoardRow(CustomPanelAPI panel, float width) {
        this.panel = panel;
        this.width = width;
    }

    public void render(float posY) {
        renderLeftElements(0, posY);
        renderRightElements(-width + SPACING, posY);
    }

    public float getHeight() {
        float leftHeight = getElementsHeight(getLeftElements());
        float rightHeight = getElementsHeight(getRightElements());
        return Math.max(leftHeight, rightHeight);
    }

    abstract protected List<BoardElement> getLeftElements();

    abstract protected List<BoardElement> getRightElements();

    private void renderLeftElements(float posX, float posY) {
        float height = getHeight();
        for (BoardElement element : getLeftElements()) {
            TooltipMakerAPI inner = panel.createUIElement(width, height, false);
            element.render(inner);
            panel.addUIElement(inner).inTL(posX, posY);
            posX += element.getWidth() + SPACING;
        }
    }

    private void renderRightElements(float posX, float posY) {
        float height = getHeight();
        for (BoardElement element : getRightElements()) {
            TooltipMakerAPI inner = panel.createUIElement(width, height, false);
            element.render(inner);
            posX += element.getWidth() + SPACING;
            panel.addUIElement(inner).inTR(posX, posY);
        }
    }

    private float getElementsHeight(List<BoardElement> elements) {
        float height = 0;
        for (BoardElement element : elements) {
            height = Math.max(height, element.getHeight());
        }
        return height;
    }
}
