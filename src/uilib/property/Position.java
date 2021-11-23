package uilib.property;

import lombok.Getter;

@Getter
public class Position extends Point2D {

    public Position(float x, float y) {
        super(x, y);
    }

    public Position(Point2D point2d) {
        super(point2d);
    }

    @Override
    public Position shift(Point2D point2d) {
        return new Position(super.shift(point2d));
    }

    @Override
    public Position unshift(Point2D point2d) {
        return new Position(super.unshift(point2d));
    }
}
