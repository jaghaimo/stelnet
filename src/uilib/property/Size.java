package uilib.property;

import lombok.Getter;

@Getter
public class Size extends Point2D {

    public Size(Point2D point) {
        super(point);
    }

    public Size(float x, float y) {
        super(x, y);
    }

    public float getWidth() {
        return getX();
    }

    public float getHeight() {
        return getY();
    }

    public Size increase(Point2D point2d) {
        return new Size(shift(point2d));
    }

    public Size reduce(Point2D point2d) {
        return new Size(unshift(point2d));
    }
}
