package uilib;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
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
        List<SkillLevelAPI> skills = getSkills();
        String title = getTitle(skills);
        new ShowSkills(skills, title).render(tooltip);
    }

    public String getTitle(List<SkillLevelAPI> skills) {
        if (skills.isEmpty()) {
            return String.format(
                "Level %d %s with no notable skills.",
                person.getStats().getLevel(),
                person.getPost().toLowerCase()
            );
        }
        return String.format(
            "Level %d %s with the following skills:",
            person.getStats().getLevel(),
            person.getPost().toLowerCase()
        );
    }

    private List<SkillLevelAPI> getSkills() {
        List<SkillLevelAPI> skills = person.getStats().getSkillsCopy();
        CollectionUtils.reduce(
            skills,
            new LogicalOr(Arrays.<Filter>asList(new SkillIsAdmin(), new SkillIsCombatOfficer()), "Any Skill")
        );
        return skills;
    }
}
