package stelnet.board.query.view.add;

import lombok.Getter;
import uilib.property.Size;

@Getter
public class SizeHelper {

    private final float width;
    private final float height;
    private final float textWidth;
    private final float groupWidth;
    private final float previewWidth;

    public SizeHelper() {
        width = 0;
        height = 0;
        textWidth = 0;
        groupWidth = 0;
        previewWidth = 0;
    }

    public SizeHelper(Size size) {
        width = size.getWidth();
        height = size.getHeight();
        textWidth = 175;
        previewWidth = 250;
        groupWidth = width - textWidth - previewWidth - 10;
    }

    public float getGroupAndTextWidth() {
        return groupWidth + textWidth;
    }
}
