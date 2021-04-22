package stelnet.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;

import stelnet.helper.LogHelper;

public class GridRenderer {

    private Size size;
    private Renderable topLeft;
    private Renderable topRight;
    private Renderable bottomLeft;
    private Renderable bottomRight;

    public GridRenderer(Size size) {
        this.size = size;
    }

    public void render(CustomPanelAPI panel) {
        render(panel, topLeft, new Position(0, 0));
        render(panel, topRight, new Position(1, 0));
        render(panel, bottomLeft, new Position(0, 1));
        render(panel, bottomRight, new Position(1, 1));
    }

    public void setTopLeft(Renderable topLeft) {
        this.topLeft = topLeft;
    }

    public void setTopRight(Renderable topRight) {
        this.topRight = topRight;
    }

    public void setBottomLeft(Renderable bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public void setBottomRight(Renderable bottomRight) {
        this.bottomRight = bottomRight;
    }

    private void render(CustomPanelAPI panel, Renderable renderable, Position selector) {
        if (renderable == null) {
            return;
        }
        Size offset = size.getDifference(renderable.getSize());
        float x = offset.getWidth() * selector.getX();
        float y = offset.getHeight() * selector.getY();
        renderable.render(panel, x, y);
        LogHelper.debug("Rendered panel with " + size + " in " + new Position(x, y));
    }
}
