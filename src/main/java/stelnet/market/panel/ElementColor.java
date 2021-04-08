package stelnet.market.panel;

import java.awt.Color;

public enum ElementColor {

    BLUE(170, 222, 255, 255), GREEN(5, 115, 10, 255), RED(255, 0, 0, 255), YELLOW(220, 185, 55, 255);

    private ElementColor(int r, int g, int b, int a) {
        color = new Color(r, g, b, a);
    }

    private Color color;

    public Color getColor() {
        return color;
    }
}
