package uilib;

import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import uilib.property.Size;

public class Line extends Button {

    public Line(float width) {
        this(width, Misc.getDarkPlayerColor());
    }

    public Line(float width, Color color) {
        super(new Size(width, 4), "", true, color, color);
    }

    @Override
    public String toString() {
        return String.format("Line(%.0f)", getSize().getWidth());
    }
}
