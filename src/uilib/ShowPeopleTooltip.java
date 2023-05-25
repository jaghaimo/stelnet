package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowPeopleTooltip implements TooltipCreator {

    private final PersonAPI person;

    @Override
    public boolean isTooltipExpandable(Object tooltipParam) {
        return false;
    }

    @Override
    public float getTooltipWidth(Object tooltipParam) {
        return 250;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
        tooltip.addPara(person.getNameString(), Misc.getBasePlayerColor(), 0);
        tooltip.addButton("", null, 250, 0, 0);
        tooltip.addSpacer(4);
        new ShowSkills(person).render(tooltip);
    }
}
