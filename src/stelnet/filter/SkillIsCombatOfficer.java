package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;

public class SkillIsCombatOfficer extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof SkillLevelAPI) {
            return acceptSkillLevel((SkillLevelAPI) object);
        }
        if (object instanceof SkillSpecAPI) {
            return acceptSkill((SkillSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptSkillLevel(SkillLevelAPI skillLevel) {
        return acceptSkill(skillLevel.getSkill());
    }

    protected boolean acceptSkill(SkillSpecAPI skill) {
        return skill.isCombatOfficerSkill();
    }
}
