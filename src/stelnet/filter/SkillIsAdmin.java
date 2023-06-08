package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class SkillIsAdmin extends SkillFilter {

    protected boolean acceptSkillLevel(SkillLevelAPI skillLevel) {
        return acceptSkill(skillLevel.getSkill());
    }

    protected boolean acceptSkill(SkillSpecAPI skill) {
        return skill.isAdminSkill();
    }
}
