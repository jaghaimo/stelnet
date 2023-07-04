package uilib2.label;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SectionHeadingColored extends AbstractLabel {

    private final String str;
    private final Color textColor;
    private final Color bgColor;
    private final Alignment align;
    private final float pad;

    @Override
    public LabelAPI addLabel(final TooltipMakerAPI tooltip) {
        return tooltip.addSectionHeading(str, textColor, bgColor, align, pad);
    }
}
