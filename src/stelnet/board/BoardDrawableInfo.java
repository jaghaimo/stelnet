package stelnet.board;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import uilib2.intel.DrawableIntelInfo;
import uilib2.label.Highlight;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class BoardDrawableInfo implements DrawableIntelInfo {

    private final String title;
    private final String description;

    @Setter
    private Highlight titleHighlight;

    @Setter
    private Highlight descriptionHighlight;

    @Override
    public void draw(TooltipMakerAPI tooltip, Color bulletColor, Color titleColor) {
        LabelAPI titleLabel = tooltip.addPara(getTitle(), titleColor, 0);
        if (titleHighlight != null) {
            titleHighlight.highlight(titleLabel);
        }
        LabelAPI descriptionLabel = tooltip.addPara(getDescription(), bulletColor, 1);
        if (descriptionHighlight != null) {
            descriptionHighlight.highlight(descriptionLabel);
        }
    }
}
