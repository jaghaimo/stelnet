package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Heading extends Renderable {

    private String title;
    private Color foregroundColor;
    private Color backgroundColor;

    public Heading(String title, Color foregroundColor, Color backgroundColor) {
        this.title = title;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        setSize(new Size(0, 10));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addSectionHeading(title, foregroundColor, backgroundColor, Alignment.MID, 10f);
    }
}
