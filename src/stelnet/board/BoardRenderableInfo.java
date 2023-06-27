package stelnet.board;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.Data;
import uilib.RenderableIntelInfo;

@Data
public class BoardRenderableInfo implements RenderableIntelInfo {

    private final String title;
    private final String description;

    @Override
    public void render(final TooltipMakerAPI info, final Color bulletColor, final Color titleColor) {
        info.addPara(getTitle(), titleColor, 0);
        info.addPara(getDescription(), bulletColor, 1);
    }
}
