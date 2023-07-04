package uilib;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import lombok.Setter;
import uilib.property.Size;

@Setter
public class Heading extends RenderableComponent {

    private final String title;
    private Color foregroundColor;
    private Color backgroundColor;
    private Alignment alignment = Alignment.MID;

    public Heading(final String title) {
        this(title, null, null);
    }

    public Heading(final String title, final float width) {
        this(title);
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT));
    }

    public Heading(final String title, final Color foregroundColor, final Color backgroundColor) {
        this.title = title;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        setSize(new Size(0, UiConstants.DEFAULT_ROW_HEIGHT));
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        if (foregroundColor == null || backgroundColor == null) {
            tooltip.addSectionHeading(title, alignment, 0);
        } else {
            tooltip.addSectionHeading(title, foregroundColor, backgroundColor, alignment, 0);
        }
    }
}
