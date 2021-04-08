package stelnet.filter.other;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;

import stelnet.filter.Filter;

public class HasSkill implements Filter<SkillLevelAPI> {

    @Override
    public boolean accept(SkillLevelAPI skill) {
        return skill.getLevel() > 0;
    }
}
