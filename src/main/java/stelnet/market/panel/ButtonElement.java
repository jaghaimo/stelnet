package stelnet.market.panel;

import java.awt.Color;

import com.fs.starfarer.api.ui.ButtonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

public class ButtonElement extends BoardElement {

    private String title;
    private String id;
    private boolean isEnabled;
    private Color mainColor;

    public ButtonElement(float width, float height, String title, String id, boolean isEnabled, Color mainColor) {
        super(width, height);
        this.title = title;
        this.id = id;
        this.isEnabled = isEnabled;
        this.mainColor = mainColor;
    }

    @Override
    public void render(TooltipMakerAPI inner) {
        Color backgroundColor = Misc.scaleColor(mainColor, 0.5f);
        ButtonAPI button = inner.addButton(title, id, mainColor, backgroundColor, width, height, 5f);
        button.setEnabled(isEnabled);
    }
}
