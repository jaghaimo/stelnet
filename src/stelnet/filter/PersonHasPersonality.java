package stelnet.filter;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.characters.PersonalityAPI;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@RequiredArgsConstructor
public final class PersonHasPersonality extends PersonOfficerFilter {

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
        PersonAPI person = Global.getSettings().createPerson();
        person.setPersonality(personalityId);
        return person.getPersonalityAPI().getDisplayName();
    }
}
