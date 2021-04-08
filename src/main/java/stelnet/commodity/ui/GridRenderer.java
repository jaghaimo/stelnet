package stelnet.commodity.ui;

import com.fs.starfarer.api.ui.CustomPanelAPI;

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
        render(panel, topLeft, new Size(0, 0));
        render(panel, topRight, new Size(1, 0));
        render(panel, bottomLeft, new Size(0, 1));
        render(panel, bottomRight, new Size(1, 1));
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

    private void render(CustomPanelAPI panel, Renderable renderable, Size selector) {
        if (renderable == null) {
            return;
        }
        Size offset = size.getDifference(renderable.getSize());
        float x = offset.getWidth() * selector.getWidth();
        float y = offset.getHeigth() * selector.getHeigth();
        renderable.render(panel, x, y);
    }
}
