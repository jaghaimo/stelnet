package stelnet.ui;

import lombok.Getter;

@Getter
public class Size {

    private final float width;
    private final float height;

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Size getDifference(Size subtrahend) {
        return new Size(width - subtrahend.getWidth(), height - subtrahend.getHeight());
    }

    @Override
    public String toString() {
        return String.format("Size(%.0f,%.0f)", width, height);
    }
}
