package uilib;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import uilib.property.Size;

@Getter
@Setter
public class Paragraph extends RenderableComponent {

    private String text;
    private Color color;
    private Color[] highlightColors = {};
    private String[] highlightStrings = {};
    private Alignment alignment = Alignment.TL;
    private float padding = 0;

    public Paragraph(final String text, final float width) {
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT));
        setColor(Misc.getTextColor());
        setText(text);
        setWithScroller(false);
    }

    public Paragraph(final String text, final float width, final float padding) {
        this(text, width);
        setPadding(padding);
    }

    public Paragraph(final String text, final float width, final float padding, final Alignment alignment) {
        this(text, width, padding);
        setAlignment(alignment);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        final LabelAPI addedText = tooltip.addPara(text, color, padding);
        if (highlightStrings.length > 0) {
            addedText.setHighlight(highlightStrings);
        }
        if (highlightColors.length > 0) {
            addedText.setHighlightColors(highlightColors);
        }
        addedText.setAlignment(alignment);
    }

    public void setHighlightColors(final Color... highlightColors) {
        this.highlightColors = highlightColors;
    }

    public void setHighlightStrings(final String... highlightStrings) {
        this.highlightStrings = highlightStrings;
    }
}
