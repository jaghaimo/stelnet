package stelnet.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Size {

    private final float width;
    private final float height;

    public Size getDifference(Size subtrahend) {
        return new Size(width - subtrahend.getWidth(), height - subtrahend.getHeight());
    }

    @Override
    public String toString() {
        return String.format("Size(%.0f,%.0f)", width, height);
    }
}
