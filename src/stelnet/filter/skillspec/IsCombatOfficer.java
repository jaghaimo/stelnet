package stelnet.filter.skillspec;

import com.fs.starfarer.api.characters.SkillSpecAPI;

public class IsCombatOfficer implements SkillSpecFilter {

    @Override
    public boolean accept(SkillSpecAPI object) {
        return object.isCombatOfficerSkill();
    }
}
