package uilib.tooltip;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonTooltip implements TooltipCreator {

    private final PersonAPI person;

    @Override
    public boolean isTooltipExpandable(Object tooltipParam) {
        return false;
    }

    @Override
    public float getTooltipWidth(Object tooltipParam) {
        return 200;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
        tooltip.addImage(person.getPortraitSprite(), 128, 4);
        PositionAPI imagePosition = tooltip.getPrev().getPosition();
        imagePosition.inTMid(4);
        tooltip.addPara(person.getPost(), 4).getPosition().inTL(0, 128 + 8);
        tooltip.addPara("Level " + String.valueOf(person.getStats().getLevel()), 2);
    }
}
