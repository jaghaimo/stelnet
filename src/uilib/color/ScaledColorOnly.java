package uilib.color;

import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ScaledColorOnly implements ColorProvider {

    private final ColorProvider color;
    private final float factor;

    @Override
    public Color get() {
        return Misc.scaleColorOnly(color.get(), factor);
    }
}
