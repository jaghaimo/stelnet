package stelnet.ui;

import java.awt.Color;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Setter;

@Setter
public class Paragraph extends AbstractRenderable {

    private final String text;
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
}
