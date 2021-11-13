package uilib;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class Heading extends RenderableComponent {

    private final String title;
    private final Color foregroundColor;
    private final Color backgroundColor;
    private Alignment alignment = Alignment.MID;

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
            tooltip.addSectionHeading(title, alignment, 0);
        } else {
            tooltip.addSectionHeading(title, foregroundColor, backgroundColor, alignment, 0);
        }
    }
}
