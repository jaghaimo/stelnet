package stelnet.commodity.ui;

public class Size {

    private final float width;
    private final float heigth;

    public Size(float width, float heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    public float getWidth() {
        return width;
    }

    public float getHeigth() {
        return heigth;
    }

    public Size getDifference(Size subtrahend) {
        return new Size(width - subtrahend.getWidth(), heigth - subtrahend.getHeigth());
    }
}
