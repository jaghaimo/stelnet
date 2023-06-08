package uilib2.label;

import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParaBasic extends AbstractLabel {

    private final String str;
    private final float pad;

    @Override
    public LabelAPI addLabel(TooltipMakerAPI tooltip) {
        return tooltip.addPara(str, pad);
    }
}
