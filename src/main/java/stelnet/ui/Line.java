package stelnet.ui;

import java.awt.Color;

public class Line extends Button {

    public Line(float width, Color color) {
        super(new Size(width, 5), "", false, color);
    }

    @Override
    public String toString() {
        return String.format("Line(%.0f)", getSize().getWidth());
    }
}
