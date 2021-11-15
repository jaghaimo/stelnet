package stelnet.filter;

import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.PersonalityAPI;
import lombok.RequiredArgsConstructor;
import stelnet.util.SettingsUtils;

@RequiredArgsConstructor
public class PersonHasPersonality extends PersonOfficerFilter {

    private final String personalityId;

    @Override
    public boolean acceptPerson(PersonAPI person) {
        if (super.acceptPerson(person)) {
            return true;
        }
        PersonalityAPI personality = person.getPersonalityAPI();
        String p = personality.getId();
        return personalityId.equals(p);
    }

    @Override
    public String toString() {
        PersonAPI person = SettingsUtils.createPerson();
        person.setPersonality(personalityId);
        return person.getPersonalityAPI().getDisplayName();
    }
}
