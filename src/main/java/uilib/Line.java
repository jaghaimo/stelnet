package uilib;

import java.awt.Color;
import uilib.property.Size;

public class Line extends Button {

    public Line(float width, Color color) {
        super(new Size(width, 5), "", true, color);
    }

    @Override
    public String toString() {
        return String.format("Line(%.0f)", getSize().getWidth());
    }
}
