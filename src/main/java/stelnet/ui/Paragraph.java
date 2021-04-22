package stelnet.ui;

import com.fs.starfarer.api.ui.TooltipMakerAPI;

public class Paragraph extends Renderable {

    private String text;
    private float width;

    public Paragraph(String title, float width) {
        this.text = title;
        this.width = width;
    }

    @Override
    public Size getSize() {
        return new Size(width, 20);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addPara(text, 0);
    }
}
