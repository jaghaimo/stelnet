package stelnet.ui.property;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Size {

    private final float width;
    private final float height;

    public Size(Position position) {
        width = position.getX();
        height = position.getY();
    }

    public Size getDifference(Size size) {
        return new Size(width - size.getWidth(), height - size.getHeight());
    }

    @Override
    public String toString() {
        return String.format("Size(%.0f,%.0f)", width, height);
    }
}
