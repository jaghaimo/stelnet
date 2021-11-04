package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PersonHasLevel extends PersonFilter {

    private final int level;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        MutableCharacterStatsAPI characterStats = person.getStats();
        int personLevel = characterStats.getLevel();
        return personLevel >= level;
    }
}
