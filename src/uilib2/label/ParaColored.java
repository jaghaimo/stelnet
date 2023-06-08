package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParaColored extends AbstractLabel {

    private final String str;
    private final Color color;
    private final float pad;

    @Override
    public LabelAPI addLabel(TooltipMakerAPI tooltip) {
        return tooltip.addPara(str, color, pad);
    }
}
