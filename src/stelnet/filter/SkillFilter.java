package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI.SkillLevelAPI;
import com.fs.starfarer.api.characters.SkillSpecAPI;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public abstract class SkillFilter extends Filter {

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

    protected abstract boolean acceptSkillLevel(SkillLevelAPI skillLevel);

    protected abstract boolean acceptSkill(SkillSpecAPI skill);
}
