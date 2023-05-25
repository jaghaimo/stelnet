package uilib;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.filter.SkillIsAdmin;
import stelnet.filter.SkillIsCombatOfficer;
import stelnet.util.CollectionUtils;

@RequiredArgsConstructor
public class ShowSkills extends RenderableComponent {

    private final PersonAPI person;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        // TODO: Move addPara away and make strings localizable
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionUtils.reduce(
            skills,
            new LogicalOr(Arrays.<Filter>asList(new SkillIsAdmin(), new SkillIsCombatOfficer()), "Any Skill")
        );
        String level;
        if (skills.isEmpty()) {
            level =
                String.format(
                    "Level %d %s with no notable skills.",
                    person.getStats().getLevel(),
                    person.getPost().toLowerCase()
                );
            tooltip.addPara(level, UiConstants.DEFAULT_BUTTON_PADDING);
            tooltip.addSpacer(UiConstants.DEFAULT_BUTTON_PADDING);
            return;
        }

        level =
            String.format(
                "Level %d %s with the following skills:",
                person.getStats().getLevel(),
                person.getPost().toLowerCase()
            );
        tooltip.addPara(level, UiConstants.DEFAULT_BUTTON_PADDING);
        tooltip.addSpacer(UiConstants.DEFAULT_BUTTON_PADDING);
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
