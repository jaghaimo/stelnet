package uilib2.intel;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import uilib2.label.Highlight;

/**
 * Simple implementation of `DrawableIntelInfo` with two lines: title and description.
 */
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class SimpleDrawableInfo implements DrawableIntelInfo {

    private final String title;
    private final String description;

    @Setter
    private Highlight titleHighlight;

    @Setter
    private Highlight descriptionHighlight;

    @Override
    public void draw(final TooltipMakerAPI tooltip, final Color bulletColor, final Color titleColor) {
        final LabelAPI titleLabel = tooltip.addPara(getTitle(), titleColor, 0);
        if (titleHighlight != null) {
            titleHighlight.highlight(titleLabel);
        }
        final LabelAPI descriptionLabel = tooltip.addPara(getDescription(), bulletColor, 1);
        if (descriptionHighlight != null) {
            descriptionHighlight.highlight(descriptionLabel);
        }
    }
}
