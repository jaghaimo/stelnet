package stelnet.ui.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Position {

    private final float x;
    private final float y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(Size size) {
        this.x = size.getWidth();
        this.y = size.getHeight();
    }

    public Position shift(Size size) {
        return new Position(x + size.getWidth(), y + size.getHeight());
    }

    @Override
    public String toString() {
        return String.format("Position(%.0f,%.0f)", x, y);
    }
}
