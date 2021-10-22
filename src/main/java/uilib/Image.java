package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Image extends AbstractRenderable {

    private final String spriteName;
    private final float width;
    private final float height;

    public Image(String spriteName) {
        this(spriteName, 0f, 0f);
    }

    public Image(String spriteName, float width) {
        this(spriteName, width, 0f);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (width == 0) {
            tooltip.addImage(spriteName, 10);
        } else if (height == 0) {
            tooltip.addImage(spriteName, width, 10);
        } else {
            tooltip.addImage(spriteName, width, height, 10);
        }
    }
}
