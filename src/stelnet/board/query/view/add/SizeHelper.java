package stelnet.board.query.view.add;

import lombok.Getter;
import uilib.property.Size;

@Getter
public class SizeHelper {

    private final float width;
    private final float height;
    private final float textWidth;
    private final float groupWidth;

    public SizeHelper() {
        width = 0;
        height = 0;
        textWidth = 0;
        groupWidth = 0;
    }

    public SizeHelper(Size size) {
        width = size.getWidth();
        height = size.getHeight();
        groupWidth = Math.max(300, width - 120);
        textWidth = width - groupWidth;
    }

    public float getGroupAndTextWidth() {
        return groupWidth + textWidth;
    }
}
