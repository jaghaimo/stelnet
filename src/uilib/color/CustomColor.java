package uilib.color;

import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomColor implements ColorProvider {

    private final int red;
    private final int green;
    private final int blue;
    private final int alpha;

    @Override
    public Color get() {
        return new Color(red, green, blue, alpha);
    }
}
