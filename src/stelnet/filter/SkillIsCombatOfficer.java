package stelnet.filter;

import com.fs.starfarer.api.characters.SkillSpecAPI;

public class SkillIsCombatOfficer extends Filter {

    @Override
    public boolean accept(Object object) {
        if (object instanceof SkillSpecAPI) {
            return acceptSkill((SkillSpecAPI) object);
        }
        return super.accept(object);
    }

    protected boolean acceptSkill(SkillSpecAPI object) {
        return object.isCombatOfficerSkill();
    }
}
