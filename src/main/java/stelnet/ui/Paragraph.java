package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Setter;

@Setter
public class Paragraph extends AbstractRenderable {

    private String text;
    private Color color;
    private Color[] highlightColors = {};
    private String[] highlightStrings = {};

    public Paragraph(String title, float width) {
        setSize(new Size(width, 20));
        setColor(Misc.getTextColor());
        setText(text);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        LabelAPI addedText = tooltip.addPara(text, color, 0);
        if (highlightStrings.length > 0) {
            addedText.setHighlight(highlightStrings);
        }
        if (highlightColors.length > 0) {
            addedText.setHighlightColors(highlightColors);
        }
    }

    public void setHighlightColors(Color... highlightColors) {
        this.highlightColors = highlightColors;
    }

    public void setHighlightStrings(String... highlightStrings) {
        this.highlightStrings = highlightStrings;
    }
}
