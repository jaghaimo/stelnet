package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class SkillIsCombatOfficer extends SkillFilter {

    @Override
    protected boolean acceptSkillLevel(final SkillLevelAPI skillLevel) {
        return acceptSkill(skillLevel.getSkill());
    }

    @Override
    protected boolean acceptSkill(final SkillSpecAPI skill) {
        return skill.isCombatOfficerSkill();
    }
}
