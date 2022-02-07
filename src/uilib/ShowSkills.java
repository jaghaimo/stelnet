package uilib;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.CollectionUtils;

@RequiredArgsConstructor
public class ShowSkills extends RenderableComponent {

    private final PersonAPI person;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionUtils.reduce(skills, new SkillIsCombatOfficer());
        for (SkillLevelAPI skill : skills) {
            showSkill(tooltip, skill);
        }
    }

    private void showSkill(TooltipMakerAPI tooltip, SkillLevelAPI skill) {
        String elite = skill.getLevel() > 1 ? "Elite " : "";
        String skillString = String.format("%s%s", elite, skill.getSkill().getName());
        TooltipMakerAPI inner = tooltip.beginImageWithText(skill.getSkill().getSpriteName(), 18);
        inner.addPara(skillString, 0, Misc.getHighlightColor(), "Elite");
        tooltip.addImageWithText(2);
    }
}
