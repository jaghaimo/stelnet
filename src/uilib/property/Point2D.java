package uilib.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Point2D {

    private final float x;
    private final float y;

    public Point2D(Point2D point2d) {
        this(point2d.getX(), point2d.getY());
    }

    public Point2D shift(Point2D point2d) {
        return new Point2D(getX() + point2d.getX(), getY() + point2d.getY());
    }

    public Point2D unshift(Point2D point2d) {
        return new Point2D(getX() - point2d.getX(), getY() - point2d.getY());
    }
}
