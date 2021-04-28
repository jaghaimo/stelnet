package stelnet.ui;

import lombok.Getter;

@Getter
public class Position {

    private final float x;
    private final float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(Size size) {
        this.x = size.getWidth();
        this.y = size.getHeight();
    }

    @Override
    public String toString() {
        return String.format("Position(%.0f,%.0f)", x, y);
    }
}
