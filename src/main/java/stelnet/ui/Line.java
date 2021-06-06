package stelnet.ui;

import java.awt.Color;

import stelnet.ui.property.Size;

public class Line extends Button {

    public Line(float width, Color color) {
        super(new Size(width, 5), "", true, color);
    }

    @Override
    public String toString() {
        return String.format("Line(%.0f)", getSize().getWidth());
    }
}
