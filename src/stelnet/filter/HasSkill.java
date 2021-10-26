package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;

@Deprecated
public class HasSkill implements Filter<SkillLevelAPI> {

    @Override
    public boolean accept(SkillLevelAPI skill) {
        return skill.getLevel() > 0;
    }
}
