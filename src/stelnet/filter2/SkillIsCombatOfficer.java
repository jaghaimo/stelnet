package stelnet.filter2;

import com.fs.starfarer.api.characters.SkillSpecAPI;

public class SkillIsCombatOfficer extends Filter {

    @Override
    public boolean accept(SkillSpecAPI object) {
        return object.isCombatOfficerSkill();
    }
}
