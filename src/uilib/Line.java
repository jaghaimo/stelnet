package uilib;

import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.ToString;
import uilib.property.Size;

@ToString
public class Line extends Button {

    public Line(float width) {
        this(width, Misc.getDarkPlayerColor());
    }

    public Line(float width, Color color) {
        super(new Size(width, 0), "", true, color, color);
    }
}
