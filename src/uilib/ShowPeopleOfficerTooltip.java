package uilib;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.CollectionUtils;

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
        String level = String.format(" %s (%d)", person.getNameString(), person.getStats().getLevel());
        tooltip.addSectionHeading(level, Alignment.LMID, 2);
        tooltip.addSpacer(4);
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionUtils.reduce(skills, new SkillIsCombatOfficer());
        for (SkillLevelAPI skill : skills) {
            addSkill(tooltip, skill);
        }
    }

    private void addSkill(TooltipMakerAPI tooltip, SkillLevelAPI skill) {
        String elite = skill.getLevel() > 1 ? "Elite " : "";
        String skillString = String.format("%s%s", elite, skill.getSkill().getName());
        TooltipMakerAPI inner = tooltip.beginImageWithText(skill.getSkill().getSpriteName(), 18);
        inner.addPara(skillString, 0, Misc.getHighlightColor(), "Elite");
        tooltip.addImageWithText(2);
    }
}
