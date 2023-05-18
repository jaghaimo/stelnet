package stelnet.board;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.Data;
import uilib2.intel.DrawableIntelInfo;

@Data
public class BoardDrawableInfo implements DrawableIntelInfo {

    private final String title;
    private final String description;

    @Override
    public void draw(TooltipMakerAPI tooltip, Color bulletColor, Color titleColor) {
        tooltip.addPara(getTitle(), titleColor, 0);
        tooltip.addPara(getDescription(), bulletColor, 1);
    }
}
