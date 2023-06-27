package stelnet.board;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uilib.RenderableIntelInfo;

@Getter
@RequiredArgsConstructor
public class IntelRenderableInfo implements RenderableIntelInfo {

    private final String title;
    private final String header1;
    private final String content1;
    private final String header2;
    private final String content2;

    public void render(final TooltipMakerAPI info, final Color bulletColor, final Color titleColor) {
        info.addPara(getTitle(), titleColor, 0);
        info.beginGridFlipped(300, 1, Misc.getTextColor(), 80, 10);
        info.addToGrid(0, 0, info.shortenString(getContent1(), 200), getHeader1(), bulletColor);
        info.addToGrid(0, 1, info.shortenString(getContent2(), 200), getHeader2(), bulletColor);
        info.addGrid(3);
    }
}
