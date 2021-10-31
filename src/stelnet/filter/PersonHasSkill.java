package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonHasSkill extends PersonFilter {

    private final String skillId;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        float skillLevel = person.getFleetCommanderStats().getSkillLevel(skillId);
        return skillLevel > 0;
    }
}
