package uilib.color;

import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AdjustedBrightness implements ColorProvider {

    private final ColorProvider color;
    private final int brightness;

    @Override
    public Color get() {
        return Misc.setBrightness(color.get(), brightness);
    }
}
