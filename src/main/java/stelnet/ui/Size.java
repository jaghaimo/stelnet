package stelnet.ui;

public class Size {

    private final float width;
    private final float height;

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Size getDifference(Size subtrahend) {
        return new Size(width - subtrahend.getWidth(), height - subtrahend.getHeight());
    }
}
