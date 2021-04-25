package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class Paragraph extends Renderable {

    private String text;
    private Color color;

    public Paragraph(String title, float width) {
        this.text = title;
        setSize(new Size(width, 20));
        setColor(Misc.getTextColor());
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addPara(text, color, 0);
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
