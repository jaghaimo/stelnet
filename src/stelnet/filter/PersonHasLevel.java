package stelnet.filter;

import com.fs.starfarer.api.characters.MutableCharacterStatsAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import lombok.RequiredArgsConstructor;
import stelnet.CommonL10n;
import stelnet.util.L10n;

@RequiredArgsConstructor
public class PersonHasLevel extends PersonOfficerFilter {

    private final int level;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        if (super.acceptPerson(person)) {
            return true;
        }
        MutableCharacterStatsAPI characterStats = person.getStats();
        int personLevel = characterStats.getLevel();
        return personLevel >= level;
    }

    @Override
    public String toString() {
        return L10n.get(CommonL10n.FILTER_OFFICER_LEVEL, level);
    }
}
