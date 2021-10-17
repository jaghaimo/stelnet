package uilib;

import java.awt.Color;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Setter;
import uilib.property.Size;

@Setter
public class Paragraph extends AbstractRenderable {

    private String text;
    private Color color;
    private Color[] highlightColors = {};
    private String[] highlightStrings = {};
    private Alignment alignment = Alignment.TL;
    private float padding = 0;

    public Paragraph(String text, float width) {
        setSize(new Size(width, 20));
        setColor(Misc.getTextColor());
        setText(text);
        setWithScroller(false);
    }

    public Paragraph(String text, float width, float padding, Alignment alignment) {
        this(text, width);
        setPadding(padding);
        setAlignment(alignment);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        LabelAPI addedText = tooltip.addPara(text, color, padding);
        if (highlightStrings.length > 0) {
            addedText.setHighlight(highlightStrings);
        }
        if (highlightColors.length > 0) {
            addedText.setHighlightColors(highlightColors);
        }
        addedText.setAlignment(alignment);
    }

    public void setHighlightColors(Color... highlightColors) {
        this.highlightColors = highlightColors;
    }

    public void setHighlightStrings(String... highlightStrings) {
        this.highlightStrings = highlightStrings;
    }
}
