package uilib;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import uilib.property.Size;

public class Heading extends AbstractRenderable {

    private final String title;
    private final Color foregroundColor;
    private final Color backgroundColor;

    public Heading(String title) {
        this(title, null, null);
    }

    public Heading(String title, Color foregroundColor, Color backgroundColor) {
        this.title = title;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        setSize(new Size(0, 10));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (foregroundColor == null || backgroundColor == null) {
            tooltip.addSectionHeading(title, Alignment.MID, 0);
        } else {
            tooltip.addSectionHeading(title, foregroundColor, backgroundColor, Alignment.MID, 0);
        }
    }
}
