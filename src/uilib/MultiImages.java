package uilib;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MultiImages extends RenderableComponent {

    private final String[] spriteNames;
    private final float width;
    private final float height;
    private float pad = UiConstants.DEFAULT_SPACER;
    private float imagePad = UiConstants.DEFAULT_BUTTON_PADDING;

    public MultiImages(float width, float height, String... spriteNames) {
        this(spriteNames, width, height);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.addImages(width, height, pad, imagePad, spriteNames);
    }
}
