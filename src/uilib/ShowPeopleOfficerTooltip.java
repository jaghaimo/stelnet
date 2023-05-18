package uilib;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowPeopleOfficerTooltip implements TooltipCreator {

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
        String level = String.format(" %s (L%d)", person.getNameString(), person.getStats().getLevel());
        tooltip.addSectionHeading(level, Alignment.LMID, 2);
        tooltip.addSpacer(4);
        tooltip.addSkillPanelOneColumn(person, 0);
        // (new ShowSkills(person)).render(tooltip);
    }
}
